# Prelim Hands-on Exam: Profile Screen

I have updated the project to strictly follow the **Prelim Hands-on Exam** requirements. All UI elements are implemented using Jetpack Compose and styled with the provided **Liceo Maroon** theme.

## Exam Requirement Checklist

- [x] **Task 1: Center the layout**: Root `Column` uses `verticalArrangement = Arrangement.Center`.
- [x] **Task 2: Circular avatar**: Implemented with initials "VJT", 120dp size, `CircleShape` clip, and theme-based background/border.
- [x] **Task 3: Full name & subtitle**: Displays "Vince Joshua Tan" and "BSIT 3-2" using the specified typography and colors.
- [x] **Task 4: The Info Card**: All information rows are grouped in a Material 3 `Card` with proper padding.
- [x] **Task 5: Reusable InfoRow**: A single `InfoRow` composable is used for all 5 fields (Name, Course, Section, Mobile, Email).
- [x] **Task 6: Verify light AND dark**: Confirmed both modes work correctly via previews.
- [x] **Theming**: No hard-coded colors; all values are read from `MaterialTheme.colorScheme` and `MaterialTheme.typography`.

## Visual Verification

````carousel
![Profile - Light Mode](file:///D:/edp-android-ra/.artifacts/20260807-113348-bd97c971-ea5a-413b-89b6-f9a127a0e32e/preview_light.png)
<!-- slide -->
![Profile - Dark Mode](file:///D:/edp-android-ra/.artifacts/20260807-113348-bd97c971-ea5a-413b-89b6-f9a127a0e32e/preview_dark.png)
````

## Implementation Details

### Reusable InfoRow
```kotlin
@Composable
fun InfoRow(icon: ImageVector, label: String, value: String) {
    Row(...) {
        Icon(..., tint = MaterialTheme.colorScheme.primary)
        Column(...) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurface)
        }
    }
}
```

### Theme Usage
The theme has been renamed to `ProfileCardLabTheme` to match the exam instructions. All colors are dynamically resolved based on the current theme mode (Light/Dark).

## How to Submit
I have already implemented the code. You can now:
1.  Run the project to confirm everything works on your device.
2.  Follow the **How to Submit** section in your instructions (Git commands) to push your work to the `prelim-handson-exam` branch.
