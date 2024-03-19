# ClockView Application

ClockViewApp is an application showcases a custom `ClockView` that displays the current time on analog clock. It includes three different screens to demonstrate the versatility and scalability of the `ClockView`.

## Features

- **Custom ClockView**: The `ClockView` is a custom view that displays the current time. It's designed to be easily customizable, with options to change the clock color, border color, and border width.

- **Three Demonstration Screens**: three screens to showcase the `ClockView` in different situations:
  - **ClockXMLFragment**: This screen shows how to add a `ClockView` to your layout using XML.
  - **MultipleClocksFragment**: This screen shows multiple `ClockView` instances of different sizes, demonstrating the scalability of the `ClockView`.
  - **ClockCodeFragment**: This screen shows how to add a `ClockView` to your layout from kotlin code.

The `ClockView` is implemented using Android's `View` class, making it easily reusable in any Android application.
It uses a `Handler` to update the time every second and maintains its state under configuration changes (e.g. screen rotations, themes changes, etc.).
The `ClockView` can be added to the layout either from code or using XML. It also supports custom attributes for customization.

| ClockXML                                                     | MultipleClocks                                               | ClockCode                                                    |
|--------------------------------------------------------------|--------------------------------------------------------------|--------------------------------------------------------------|
| ![Screenshot 1](/screenshots/Screenshot_20240319-131544.png) | ![Screenshot 2](/screenshots/Screenshot_20240319-131604.png) | ![Screenshot 3](/screenshots/Screenshot_20240319-131623.png) |
