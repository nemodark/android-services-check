import { AndroidPermissions } from "@ionic-native/android-permissions";
import { LocationAccuracy } from "@ionic-native/location-accuracy";
import { Capacitor } from "@capacitor/core";

const LocationService = {
  async checkGPSPermission () {
    return await new Promise((resolve, reject) => {
      if (Capacitor.isNativePlatform) {
        AndroidPermissions.checkPermission(AndroidPermissions.PERMISSION.ACCESS_FINE_LOCATION)
          .then((result) => {
            if (result.hasPermission) {
              //If having permission show 'Turn on GPS' dialogue
              resolve(true);
            } else {
              resolve(false);
            }
          })
          .catch((err) => {
            reject(err);
            alert(err);
          });
      } else {
        resolve(true);
      }
    })
  },

  async requestGPSPermission () {
    return await new Promise((resolve, reject) => {
      LocationAccuracy.canRequest().then((canRequest) => {
        if(canRequest) {
          resolve('CAN_REQUEST');
        } else {
          // Show the gps permission request dialogue
          AndroidPermissions.requestPermission(AndroidPermissions.PERMISSION.ACCESS_FINE_LOCATION)
            .then(
              (result) => {
                if (result.hasPermission) {
                  //call method to turn on GPS
                  resolve('GOT_PERMISSION');
                } else {
                  resolve('DENIED_PERMISSION');
                }
              },
              error => {
                alert('requestPermission Error requesting location permissions ' + error)
              }
            )
            .catch((err) => {
              reject(err);
            })
        }
      })
    })
  },

  async askToTurnOnGPS () {
    return await new Promise((resolve) => {
      LocationAccuracy.canRequest().then((canRequest) => {
        if (canRequest) {
          //the accuracy option will be ignored by iOS and will work only with android
          LocationAccuracy.request(LocationAccuracy.REQUEST_PRIORITY_HIGH_ACCURACY)
            .then(
            () => {
              resolve(true);
            },
            error => {
              resolve(error);
            })
        } else {
          resolve(false);
        }
      });
    });
  }
}

export default LocationService;