# TamatemWebView

in this implementation i have used a native webView to show the provided website with three actions, back, foreword and refresh
those three actions are responsible to communicate with the website itself and fire the actions as expected using the javascript code

the app has a simple ui :
- main screen with a simple button to be clicked by the user to open the modal view (which i choose to be a Fragment Dialog)
- Dialog (Fragment Dialog) which has some views such as : webView, 4 AppcompatImageViews each one responsible for an action.

The webView has some configurations to fit the required points such as showing it and controlling the internal actions in the website from the Android platform.

This is a brief summary of the existing implementation.

Demo Video : 

[tamatem1.webm](https://github.com/diaanajjar95/TamatemWebView/assets/41286140/946b2404-ebca-45e6-b4eb-9a1cb2a09c7f)
