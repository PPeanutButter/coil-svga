# Coil-SVGA
> A SVGA Decoder For [Coil](https://github.com/coil-kt/coil?tab=readme-ov-file)

## Preview
![](preview/screen.gif)

## Code
> This code is **experimental** for now

```kotlin
Row {
    SVGAImage(
        modifier = Modifier.size(100.dp, 100.dp),
        url = "https://res.weplayapp.com/conf/32FA4681-12C3-4115-B473-8BCBB5ADC8AF.svga",
    )
    UserHeadImage(
        user = User(
            R.drawable.head,
            "https://res.weplayapp.com/conf/32FA4681-12C3-4115-B473-8BCBB5ADC8AF.svga"
        ),
        modifier = Modifier.size(100.dp, 100.dp)
    )
    SVGAImage(
        modifier = Modifier.size(60.dp, 100.dp),
        url = "https://res.weplayapp.com/conf/43EB4E6A-834B-4442-A35C-4E558FA23B93.svga",
        entities = mapOf("head" to ImageBitmap.imageResource(id = R.drawable.head).asAndroidBitmap())
    )
}
```
