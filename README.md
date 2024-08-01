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
        url = "https://xxx.svga",
    )
    UserHeadImage(
        user = User(
            R.drawable.head,
            "https://xxx.svga"
        ),
        modifier = Modifier.size(100.dp, 100.dp)
    )
    SVGAImage(
        modifier = Modifier.size(60.dp, 100.dp),
        url = "https://xxx.svga",
        entities = mapOf("head" to ImageBitmap.imageResource(id = R.drawable.head).asAndroidBitmap())
    )
}
```
