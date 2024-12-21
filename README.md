
# EcommerceApp

This repository contains the codebase for the Ecommerce Android application. The app is collaboratively developed by three team members: **Baruk**, **Muthoni**, and **Brian**, each responsible for specific modules. Below is the project structure, color scheme, and instructions for consistency.

---

## Project Structure

### Main Directories
```
EcommerceApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ecommerceapp/
│   │   │   │   ├── auth/                  # Baruk's work on Auth screens
│   │   │   │   ├── cart/                  # Muthoni's work on Cart and Checkout
│   │   │   │   ├── account/               # Muthoni's work on Account Management
│   │   │   │   ├── home/                  # Brian's work on Home, Splash, Logo
│   │   │   │   ├── categories/            # Brian's work on Categories/Collections
│   │   │   │   ├── navigation/            # Brian's work on Tab Navigator
│   │   │   ├── res/
│   │   │   │   ├── layout/                # All XML layouts
│   │   │   │   ├── values/                # Colors, Strings, Dimens
│   │   │   └── AndroidManifest.xml
├── build.gradle
└── settings.gradle
```

---

## Responsibilities and File Details

### **Baruk** - Authentication and Contact  
- **Auth Module**:  
  - `LoginActivity.java`: Handles user login functionality.  
  - `RegisterActivity.java`: Handles user registration functionality.  
  - `AuthUtils.java`: Contains helper methods for authentication.  
- **Contact Screen**:  
  - `ContactActivity.java`: Implements a screen where users can contact support.  
  - `activity_contact.xml`: XML layout for the contact screen.

### **Muthoni** - Cart, Checkout, and Account Management  
- **Cart and Checkout Module**:  
  - `CartActivity.java`: Manages the shopping cart screen.  
  - `CartAdapter.java`: RecyclerView adapter for cart items.  
  - `CheckoutActivity.java`: Handles checkout process.  
  - `CartUtils.java`: Contains helper methods for cart management.  
- **Account Management**:  
  - `AccountActivity.java`: Displays user account details and settings.  
  - `AccountUtils.java`: Helper methods for managing account-related logic.

### **Brian** - Home, Categories, and Navigation  
- **Home and Splash Screens**:  
  - `SplashActivity.java`: Launch screen with animations.  
  - `HomeActivity.java`: Displays the home page with products and collections.  
  - `LogoActivity.java`: Handles logo display.  
- **Categories/Collections**:  
  - `CategoryActivity.java`: Displays categories or collections.  
  - `CategoryAdapter.java`: RecyclerView adapter for category items.  
- **Navigation**:  
  - `TabNavigator.java`: Implements tab navigation.  
  - `BottomNavigationBar.java`: Custom bottom navigation bar.

---

## Color Scheme

To ensure a consistent UI design, use the following color palette throughout the app:

| Color          | Hex Code   | Description            |
|-----------------|------------|-----------------------|
| Primary Coral   | `#FF6F6F` | Warm Coral             |
| Secondary Dark  | `#142626` | Dark Green-Black       |
| Accent Mauve    | `#D4B8B8` | Muted Pink-Mauve       |
| Cool Mint       | `#A6D3D3` | Soft Mint-Turquoise    |
| Deep Teal       | `#004C4C` | Bold Teal              |


### Usage in `colors.xml`
```xml
<resources>
    <color name="primary_coral">#FF6F6F</color>
    <color name="secondary_dark">#142626</color>
    <color name="accent_mauve">#D4B8B8</color>
    <color name="cool_mint">#A6D3D3</color>
    <color name="deep_teal">#004C4C</color>
</resources>

```

---

## Development Guidelines

### 1. **Coding Standards**  
- Follow Java naming conventions for classes, variables, and methods.  
- Use comments to explain complex logic or unusual decisions.  
- Test your modules independently before integration.  

### 2. **Layouts and UI Consistency**  
- Use the defined color scheme in all layouts.  
- Follow Material Design principles for spacing, padding, and fonts.  
- Ensure responsiveness for all screen sizes.

### 3. **Integration**  
- Use `CartUtils`, `AuthUtils`, and `AccountUtils` for shared logic to minimize code duplication.  
- Ensure smooth transitions between screens using consistent intents.  
- Avoid hardcoding values; use `strings.xml` and `dimens.xml` for text and dimensions.

### 4. **Version Control**  
- Use Git for collaboration. Commit frequently with meaningful messages.  
- Branch naming convention:  
  - `feature/<module>` (e.g., `feature/auth`)  
  - `bugfix/<module>` (e.g., `bugfix/cart`)  

### 5. **Testing**  
- Test each module on multiple devices or emulators.  
- Perform integration testing to ensure modules work seamlessly together.

---

## Build and Run Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
2. Open the project in Android Studio.  
3. Sync Gradle files.  
4. Build the project and run it on an emulator or device.
