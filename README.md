## Project structure
```bash
EcommerceApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/ecommerceapp/
│   │   │   │   ├── auth/                  # Baruk's work on Auth screens
│   │   │   │   │   ├── LoginActivity.java
│   │   │   │   │   ├── RegisterActivity.java
│   │   │   │   │   └── AuthUtils.java
│   │   │   │   ├── cart/                  # Muthoni's work on Cart and Checkout
│   │   │   │   │   ├── CartActivity.java
│   │   │   │   │   ├── CartAdapter.java
│   │   │   │   │   ├── CheckoutActivity.java
│   │   │   │   │   └── CartUtils.java
│   │   │   │   ├── account/               # Muthoni's work on Account Management
│   │   │   │   │   ├── AccountActivity.java
│   │   │   │   │   └── AccountUtils.java
│   │   │   │   ├── home/                  # Brian's work on Home, Splash, Logo
│   │   │   │   │   ├── SplashActivity.java
│   │   │   │   │   ├── HomeActivity.java
│   │   │   │   │   └── LogoActivity.java
│   │   │   │   ├── categories/            # Brian's work on Categories/Collections
│   │   │   │   │   ├── CategoryActivity.java
│   │   │   │   │   └── CategoryAdapter.java
│   │   │   │   ├── navigation/            # Brian's work on Tab Navigator
│   │   │   │   │   ├── TabNavigator.java
│   │   │   │   │   └── BottomNavigationBar.java
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_splash.xml
│   │   │   │   │   ├── activity_home.xml
│   │   │   │   │   ├── activity_login.xml
│   │   │   │   │   ├── activity_register.xml
│   │   │   │   │   ├── activity_cart.xml
│   │   │   │   │   ├── activity_checkout.xml
│   │   │   │   │   └── activity_account.xml
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── strings.xml
│   │   │   │   │   └── dimens.xml
│   │   │   └── AndroidManifest.xml
├── build.gradle
└── settings.gradle

```