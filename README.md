# MedChat for Clinicians

An Android Compose starter for a secure chat app tailored to medical professionals. The app includes a protocol-aware bot stub that can surface guidance from care pathways such as sepsis, stroke, or chest pain.

## Features
- Compose-based chat surface optimized for clinician/bot conversations.
- ViewModel-driven state with simulated protocol lookup via `InMemoryProtocolRepository`.
- Theming aligned with Material 3 for quick iteration against your Google Stitch mockups.

## Running locally
1. Open the project in Android Studio (Giraffe or newer).
2. Let the IDE download the Android Gradle Plugin 8.2+ and Kotlin 1.9+ toolchain.
3. Run the `app` configuration on an emulator or device (API 26+).

## Next steps
- Replace `InMemoryProtocolRepository` with a real service that queries your protocol knowledge base.
- Wire authentication/SSO if targeting regulated care settings.
- Map your Google Stitch layouts to Compose components inside `MainActivity` and `ui` package.
