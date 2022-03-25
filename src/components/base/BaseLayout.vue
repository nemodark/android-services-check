<template>
  <ion-page>
    <ion-header>
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button :default-href="pageDefaultBackLink"></ion-back-button>
        </ion-buttons>
        <ion-title>{{ pageTitle }}</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content :class="classes">
      <ion-loading :is-open="loading" message="Please wait..." :duration="timeout"></ion-loading>
      <slot />
    </ion-content>
  </ion-page>
</template>

<script>
import { 
IonPage, 
IonHeader, 
IonToolbar, 
IonTitle, 
IonContent, 
IonBackButton,
IonButtons,
IonLoading
} from '@ionic/vue';

export default {
  props: {
    pageTitle: {
      type: String,
      default: "Map App"
    },
    pageDefaultBackLink: {
      type: String,
      default: '/'
    },
    classes: {
      type: String,
      default: ''
    },
    loading: {
      type: Boolean,
      default: false
    },
    timeout: {
      type: String,
      default: "3000"
    }
  },
  components: {
    IonPage,
    IonHeader,
    IonToolbar,
    IonTitle,
    IonContent,
    IonBackButton,
    IonButtons,
    IonLoading
  }
}
</script>