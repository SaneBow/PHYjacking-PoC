# PHYjacking PoC Apps

This project contains several Android apps to demonstrate different [PHYjacking](https://mobitec.ie.cuhk.edu.hk/phyjacking) attacks.

- `TransAttack`: the most basic attack that exploits the `translucent` property to create a malicious covering window. This attack is tested to work with the fingerprint victim apps on Android 8.1.
- `SplitAttack`: use the multi-window (or split-screen) feature to bypass the fingerprint API mitigation added in Android 9.
- `RaceAttack`: exploit a race-condition bug in the Android Lifcycle management to bypass both app and system level mitigations.
- `VictimApp`: This is a simple demo app using the `FingerprintManager` API provided since Android 6.0 Marshmallow. It has an dummy fingerprint authentication interface and serves as the victim app. Code is modified based on this [repo](https://github.com/rahulmmohan/Fingerprint-Authentication-Demo).
