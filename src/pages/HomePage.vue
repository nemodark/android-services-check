<template>
  <base-layout page-title="Test App">
    <ion-list>
      <ion-item router-link="/geo-location">Check Location</ion-item>
      <ion-item router-link="/check-datetime">Check Date & Time</ion-item>
    </ion-list>
  </base-layout>
</template>

<script>
import { IonList, IonItem } from '@ionic/vue';
import BaseLayout from '@/components/base/BaseLayout.vue';

export default {
  components: {
    BaseLayout,
    IonItem,
    IonList
  }  
}
</script>