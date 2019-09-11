![Screenshot Android](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/android_cover.png?raw=true)

# 📲 How to Install

#### Local deployment

With this command you can generate a local version for testing:

```./gradlew publishLocal```

# 🐒 How to use

### 1 - Android Studio

Add this line to your app's build.gradle inside the dependencies section:

```implementation 'com.mercadolibre.android.mlbusinesscomponents:1.0.0'```

### 2 - Use your UI component.
Choose and instantiate your component.


# 📦 COMPONENTS
Each component is a subclass of ```ConstraintLayout```.

## 1️⃣ - MLBusinessLoyaltyRingView Component
This component allow you to show the progress ring of points, a label and actionable button. The most common use of this component is to show a user's progress within the loyalty program.

#### Visual Example:
![MLBusinessLoyaltyRingView](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/loyaltyRingViewComponent.png?raw=true)

### MLBusinessLoyaltyRingView init
You need to set `MLBusinessLoyaltyRingData` interface. This interface allow you to populate the draw data into component. (Ring progress percent, ring color, label text, button title and button deeplink). You can be informed when the user presses the button of the component and receive the deeplink previously sent in `MLBusinessLoyaltyRingData`. Just add `OnClickLoyaltyRing` callback.

```java
MLBusinessLoyaltyRingView ringView = findViewById(R.id.ringView);
ringView.init(new MLBusinessLoyaltyRingDataSample(), new MLBusinessLoyaltyRingView.OnClickLoyaltyRing() {
            @Override
            public void onClickLoyaltyButton(@NonNull final String deepLink) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
            }
        });
```

### MLBusinessLoyaltyRingData Interface
This interface allow you to providade the proper data to draw `MLBusinessLoyaltyRingView`. You can setup ring progress percent, ring color, label text, button title and button deeplink. Each value is mandatory.

#### Definition
```java
public interface MLBusinessLoyaltyRingData {
    String getRingHexaColor();
    int getRingNumber();
    float getRingPercentage();
    String getTitle();
    String getButtonTitle();
    String getButtonDeepLink();
}
```

#### Implementation Example
Implementation of `MLBusinessLoyaltyRingDataSample` example:
```java
public class MLBusinessLoyaltyRingDataSample implements MLBusinessLoyaltyRingData {
    @Override
    public String getRingHexaColor() {
        return "#3483FA";
    }

    @Override
    public int getRingNumber() {
        return 3;
    }

    @Override
    public float getRingPercentage() {
        return 60f;
    }

    @Override
    public String getTitle() {
        return "Sumaste 20 Mercado Puntos";
    }

    @Override
    public String getButtonTitle() {
        return "Mis beneficios";
    }

    @Override
    public String getButtonDeepLink() {
        return "https://www.mercadolibre.com.ar/";
    }
}
```

## 2️⃣ - MLBusinessDiscountBoxView Component
This component allow you to show a group of N items in a grid system (3 cols by default). You can add a title and subtitle for the main component. Also, you can provide imageUrl, title, subtitle, deepLinkItem and trackId for each item. This component is responsible for knowing and setting your own height based on number of cols and item quantity.
#### Visual Example:
![MLBusinessDiscountBoxView](https://github.com/mercadolibre/mlbusiness-components-android/blob/master/documentation/images/discountBoxViewComponent.png?raw=true)

### MLBusinessDiscountBoxView init
You need to set `MLBusinessDiscountBoxData` interface. This interface allow you to populate the draw data into component. (Title, subtitle for the main component and imageUrl, title, subtitle, deepLinkItem and trackId for each item). You can be informed when the user presses the item of the component and receive the deeplink and trackId previously sent in `MLBusinessSingleItem`. Additionally receive the index of the selected item. Just add `OnClickDiscountBox` callback.

```java
MLBusinessDiscountBoxView discountBoxView = findViewById(R.id.discountView);
discountBoxView.init(new MLBusinessDiscountBoxDataSample(),
            new MLBusinessDiscountBoxView.OnClickDiscountBox() {
                @Override
                public void onClickDiscountItem(final int index, @Nullable final String deepLink,
                    @Nullable final String trackId) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(deepLink)));
                }
            });
```

### MLBusinessDiscountBoxData Interface
This interface allow you to providade the proper data to draw `MLBusinessDiscountBoxView`. You can setup title and subtitle for the main component and a list of `MLBusinessSingleItem` that represent each element of the cell.

#### Definition
```java
public interface MLBusinessDiscountBoxData {
    @Nullable
    String getTitle();
    @Nullable
    String getSubtitle();
    @NonNull
    List<MLBusinessSingleItem> getItems();
}
```

Implementation of `MLBusinessDiscountBoxDataSample` example:
```java
public class MLBusinessDiscountBoxDataSample implements MLBusinessDiscountBoxData {

    @Nullable
    @Override
    public String getTitle() {
        return "200 descuentos";
    }

    @Nullable
    @Override
    public String getSubtitle() {
        return "35 exclusivos nivel 3";
    }

    @NonNull
    @Override
    public List<SingleItem> getItems() {
        return DataSampleUtils.getItems();
    }
}
```
### MLBusinessSingleItem
This interface represents the element of each cell for `MLBusinessDiscountBoxView`.
Each element contains imageUrl, title, subtitle, deepLinkItem and trackId.

#### Definition
```java
public interface MLBusinessSingleItem {
    String getImageUrl();
    String getTitleLabel();
    String getSubtitleLabel();
    @Nullable
    String getDeepLinkItem();
    @Nullable
    String getTrackId();
}
```

Implementation of `SingleItemDataSample` example:

```java
public class SingleItemDataSample implements MLBusinessSingleItem {

    @Override
    public String getImageUrl() {
        return "https://upload.wikimedia.org/wikipedia/commons/thumb/3/36/McDonald%27s_Golden_Arches.svg/1200px-McDonald%27s_Golden_Arches.svg.png";
    }

    @Override
    public String getTitleLabel() {
        return "Hasta";
    }

    @Override
    public String getSubtitleLabel() {
        return "$ 200";
    }

    @Nullable
    @Override
    public String getDeepLinkItem() {
        return "https://www.mercadopago.com.ar/";
    }

    @Nullable
    @Override
    public String getTrackId() {
        return null;
    }
}
```

## 🔠 Font and color customization.
We use `MLUI` open source library to customize accent colors and font labels. In order to change those values check the documentation of `MLUI` stylesheet protocol.
https://github.com/mercadolibre/fury_mobile-android-ui

## 😉 Next steps?
* [ ] Bitrise for releases.
* [ ] Codebeat
* [ ] AndroidLint.

## 📋 Supported OS & SDK Versions
* Android 4.1 (nivel de API 16)
* Android Studio 3.1+

## 🔮 Project Example
This project include a Android example project using `MLBusinessComponents` basic components.

## ❤️ Feedback
- Feel free to contribute or send feedback. Fork this project and propose your own fixes, suggestions and open a pull request with the changes.

## 👨🏻‍💻 Author
- Jorge Gonzalez / jorge.gonzalez@mercadolibre.com

## 👮🏻 License

```
MIT License

Copyright (c) 2019 - Mercado Pago / Mercado Libre

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
