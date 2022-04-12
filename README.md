# [Request-Permission-Launcher](https://developer.android.com/training/permissions/requesting)
Here you use registerForActivityResult() method instead onRequestPermissionsResult() as alternative of onRequestPermissionsResult()

## Usage:
First declare your permissions in the manifest. Example

```xml   
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_CONTACTS" />
```
**Single permission:**
```kotlin
 requestSinglePermissionLauncher.launch(Manifest.permission.READ_CONTACTS)
 ```
```kotlin
private val requestSinglePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            Log.d(TAG, "READ_CONTACTS: $isGranted")
            binding.textViewSingle.text = "READ_CONTACTS: $isGranted"
            if (isGranted) {
              // Do something..
            } else {
                // Do something...
            }
        }
```


**Multiple permissions:**
```kotlin
 requestMultiplePermissionLauncher.launch(
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            )
```
```kotlin
 private val requestMultiplePermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            Log.d(TAG, ": $result")
            binding.textViewMultiple.text =
                "READ_EXTERNAL_STORAGE: ${result[Manifest.permission.READ_EXTERNAL_STORAGE]} & CAMERA: ${result[Manifest.permission.CAMERA]}"
            Log.d(
                TAG,
                ": CAMERA: ${result[Manifest.permission.CAMERA]}, STORAGE: ${result[Manifest.permission.READ_EXTERNAL_STORAGE]}"
            )
        }
```
## Output
<img src="https://github.com/livin-bad/Request-Permission-Launcher/blob/main/doc/Runtime%20Permission.gif" width="300">
