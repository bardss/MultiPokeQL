import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        // Initialize Koin when the app starts
        Main_iosKt.doInitApp()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
