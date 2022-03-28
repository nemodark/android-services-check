<template>
  <base-layout page-title="Date & Time" classes="ion-padding" :loading="loading">
  <h2>Check Date & Time Automation</h2>
  <div>Checks if Date and time is set to automatic</div>
  <div v-show="isAutomatic != null">
    Network Provided Time: <ion-text :color="isAutomatic ? 'success' : 'danger'">{{ isAutomatic ? 'Enabled' : 'Disabled' }}</ion-text>
  </div>
    <ion-button @click="checkDateTime">Check Date & Time</ion-button>
  </base-layout>
</template>

<script>
import { IonButton, IonText } from '@ionic/vue';
import BaseLayout from '@/components/base/BaseLayout.vue';
import VerifyDateTimePlugin from '@/plugins/VerifyDateTimePlugin';
import { Capacitor } from '@capacitor/core';
import { Toast } from '@capacitor/toast';

export default {
  name: "GeoLocation",
  components: {
    BaseLayout,
    IonButton,
    IonText
  },
  data() {
    return {
      location: null,
      isAutomatic: null,
      loading: false
    }
  },
  
  methods : {

    async checkDateTime() {
      if (Capacitor.isNativePlatform) {
      const isAutomatic = await VerifyDateTimePlugin.isAutomaticChecked();

      this.isAutomatic = isAutomatic
      } else {
        await Toast.show({
          text: 'This feature only works on android or ios'
        })
      }
    },
  }
}
</script>