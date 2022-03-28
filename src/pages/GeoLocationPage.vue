<template>
  <base-layout page-title="Location Checker" classes="ion-padding" :loading="loading">
  <h2>Location check</h2>
  <div>Checks if location is enabled on this device</div>
  <pre v-if="!loading">{{ location.lat }} : {{ location.lng }}</pre>
  <pre v-else>Fetching location...</pre>
  <div v-if="isGpsEnabled !== null">
    Locations Service: <ion-text :color="isGpsEnabled ? 'success' : 'danger'">{{ isGpsEnabled ? 'Enabled' : 'Disabled' }}</ion-text>
  </div>
  <div v-if="isMock !== null">
    Mock Locations: <ion-text :color="isMock ? 'danger' : 'success'">{{ isMock ? 'Enabled' : 'Disabled' }}</ion-text>
  </div>
    <ion-button @click="checkPermissions">Check Location Service</ion-button>
  </base-layout>
</template>

<script>
import { IonButton, IonText } from '@ionic/vue';
import { Geolocation } from '@capacitor/geolocation';
import { Capacitor } from '@capacitor/core';
import { Toast } from '@capacitor/toast';
import BaseLayout from '@/components/base/BaseLayout.vue';
import LocationService from '@/plugins/Location';
import MockLocations from '@/plugins/MockLocationsPlugin';

export default {
  name: "GeoLocation",
  components: {
    BaseLayout,
    IonButton,
    IonText
  },
  data() {
    return {
      isMock: null,
      loading: false,
      location: {
          lat: 12.934485599999999,
          lng: 77.6192336,
      },
      isGpsEnabled: null
    }
  },
  
  methods : {

    async checkPermissions () {
      const hasPermission = await LocationService.checkGPSPermission();
      if (hasPermission) {
        if (Capacitor.isNativePlatform) {
          const canUseGPS = await LocationService.askToTurnOnGPS();
          this.postGPSPermission(canUseGPS);
        } else {
          this.postGPSPermission(true);
        }
      } else {
        const permission = await LocationService.requestGPSPermission();
        if (permission === 'CAN_REQUEST' || permission === 'GOT_PERMISSION') {
          if (Capacitor.isNativePlatform) {
            const canUseGPS = await LocationService.askToTurnOnGPS();
            console.log("Test " + canUseGPS);
            this.postGPSPermission(canUseGPS);
          } else {
            this.postGPSPermission(true);
          }
        } else {
          await Toast.show({
            text: 'User denied location permission'
          })
        }
      }
    },

    async postGPSPermission (canUseGPS) {
      if (canUseGPS) {
        this.isGpsEnabled = true
        this.getLocation();
      } else {
        this.isGpsEnabled = false
        await Toast.show({
          text: 'Please turn on GPS to get location'
        })
      }
    },

    async getLocation() {
      this.loading = true

      this.checkMockLocation();

      Geolocation.getCurrentPosition({
        enableHighAccuracy: true,
        timeout: 30000,
      }).then((response) => {
          this.location = {
            lat: response.coords.latitude,
            lng: response.coords.longitude
          }
          this.loading = false
        })
        .catch((error) => {
          console.log('Error getting location', error);
        });
    },

    async checkMockLocation() {
      const isMock = await MockLocations.checkMockLocation({
        action: 'check'
      });

      this.isMock = isMock
    },
  },
}
</script>