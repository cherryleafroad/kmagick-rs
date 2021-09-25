package com.cherryleafroad.kmagick

class MagickWandException(message: String) : MagickException(message)

@Suppress("unused", "PrivatePropertyName", "MemberVisibilityCanBePrivate")
@kotlin.ExperimentalUnsignedTypes
class MagickWand {
    init {
        new()
    }

    /**
     * Holds the pointer to internal object in memory.
     */
    private var handle: ULong? = null

    /**
     * Call the internal function to create the new wand.
     */
    private external fun new()

    /**
     * Check to see if this is still the correct wand.
     */
    @Throws(MagickWandException::class)
    external fun isWand(): Boolean

    /**
     * Clone the wand into a new one.
     */
    @Throws(MagickWandException::class)
    external fun clone(): MagickWand

    /**
     * While this automatically gets called by the `finalize()` destructor,
     * `finalize()` is not guaranteed to be called at all, nor called on time.
     * It's recommended to manually destroy all wands when finished.
     */
    external fun destroy()

    /**
     * While this is here to automatically call the destructor, due to
     * the way Kotlin/Java works, it's not guaranteed to be called at all,
     * or called on time. It is not recommended relying on this to destroy
     * the wand consistently/timely.
     */
    protected fun finalize() {
        destroy()
    }

    /**
     * Adds a blank image canvas of the specified size and background color to the wand.
     *
     * @param width The width of the image.
     * @param height The hiehgt of the image.
     * @param background The background color of the image.
     */
    @Throws(MagickWandException::class)
    external fun newImage(width: ULong, height: ULong, background: PixelWand)

    /**
     * The limit for a particular resource.
     *
     * @param type The type of resource.
     * @param limit The maximum limit for the resource.
     */
    @Throws(MagickWandException::class)
    external fun setResourceLimit(type: ResourceType, limit: ULong)

    /**
     * Associates one or options with the wand (e.g. setOption("jpeg:perserve","yes")).
     *
     * @param key The option key.
     * @param value The option value.
     */
    @Throws(MagickWandException::class)
    external fun setOption(key: String, value: String)

    /**
     * Annotates an image with text.
     *
     * @param drawingWand The drawing wand to use for annotation.
     * @param x X ordinate to left of text.
     * @param y Y ordinate to text baseline.
     * @param angle Rotate text relative to this angle.
     * @param text The text to draw.
     */
    @Throws(MagickWandException::class)
    external fun annotateImage(drawingWand: DrawingWand, x: Double, y: Double, angle: Double, text: String)

    /**
     * Adds a clone of the images from the second wand and inserts them into the first wand.
     * Use `MagickSetLastIterator()`, to append new images into an existing wand, current image will be set to last image
     * so later adds with also be appended to end of wand.
     * Use `MagickSetFirstIterator()` to prepend new images into wand, any more images added will also be prepended before
     * other images in the wand. However, the order of a list of new images will not change.
     * Otherwise, the new images will be inserted just after the current image, and any later image will also be added
     * after this current image but before the previously added images. Caution is advised when multiple image adds are
     * inserted into the middle of the wand image list.
     *
     * @param addWand The wand that contains the image list to be added.
     */
    @Throws(MagickWandException::class)
    external fun addImage(addWand: MagickWand)

    /**
     * Append the images in a wand from the current image onwards, creating a new wand with the single image result.
     * This is affected by the gravity and background settings of the first image.
     *
     * @param stack By default, images are stacked left-to-right. Set stack to true to stack them top-to-bottom.
     */
    @Throws(MagickWandException::class)
    external fun appendAll(stack: Boolean)

    /**
     * Adds a label to your image.
     *
     * @param label The image label.
     */
    @Throws(MagickWandException::class)
    external fun labelImage(label: String)

    /**
     * Writes an image or image sequence.
     *
     * @param path The path to the image.
     * @param adjoin Join images into a single multi-image file.
     */
    @Throws(MagickWandException::class)
    external fun writeImages(path: String, adjoin: Boolean)

    /**
     * Reads an image or image sequence. The images are inserted just before the current image pointer position.
     *
     * @param path The path to the image.
     */
    @Throws(MagickWandException::class)
    external fun readImage(path: String)

    /**
     * Reads an image or image sequence from a [blob]. In all other respects it is like `readImage()`.
     */
    @Throws(MagickWandException::class)
    external fun readImageBlob(blob: UByteArray)

    /**
     * Is the same as `readImage()` except the only valid information returned is the image width, height, size, and
     * format. It is designed to efficiently obtain this information from a file without reading the entire image
     * sequence into memory.
     *
     * @param path The path to the image.
     */
    @Throws(MagickWandException::class)
    external fun pingImage(path: String)

    /**
     * Pings an image or image sequence from a [blob].
     */
    @Throws(MagickWandException::class)
    external fun pingImageBlob(blob: UByteArray)

    /**
     * Compares two images and returns pair (distortion, diffImage)
     * diffImage is null if distortion == 0
     *
     * @param reference Reference wand.
     * @param metric The metric.
     * @return A pair containing the computed distortion(Double) and the diffImage(MagickWand) if there was a difference
     */
    @Throws(MagickWandException::class)
    external fun compareImages(reference: MagickWand, metric: MetricType): Pair<Double, MagickWand?>

    /**
     * Compose another image onto self at (x,y) using composition_operator
     *
     * @param sourceWand The source wand holding the image.
     * @param compose Composite operator affects how the composite is applied to the image. The default is Over.
     * @param clipToSelf Set to true to limit composition to area composed.
     * @param x The column offset of the composited image.
     * @param y The row offset of the composited image.
     */
    @Throws(MagickWandException::class)
    external fun compositeImage(
            sourceWand: MagickWand,
            compose: CompositeOperator,
            clipToSelf: Boolean,
            x: Long,
            y: Long
    )

    /**
     * Replaces colors in the image from a color lookup table.
     *
     * @param clutWand The clut image.
     * @param method The pixel interpolation method.
     */
    @Throws(MagickWandException::class)
    external fun clutImage(clutWand: MagickWand, method: PixelInterpolateMethod)

    /**
     * replaces colors in the image from a Hald color lookup table. A Hald color lookup table is a 3-dimensional color
     * cube mapped to 2 dimensions. Create it with the HALD coder. You can apply any color transformation to the Hald
     * image and then use this method to apply the transform to the image.
     *
     * @param haldWand The hald CLUT image.
     */
    @Throws(MagickWandException::class)
    external fun haldClutImage(haldWand: MagickWand)

    /**
     * Evaluate expression for each pixel in the image.
     *
     * @param expression The expression.
     */
    @Throws(MagickWandException::class)
    external fun fxImage(expression: String)

    /**
     * Sets the size of the magick wand. Set it before you read a raw image format such as RGB, GRAY, or CMYK.
     *
     * @param columns The width in pixels
     * @param rows The rows in pixels.
     */
    @Throws(MagickWandException::class)
    external fun setSize(columns: ULong, rows: ULong)

    /**
     * Adjusts the levels of an image by scaling the colors falling between specified white and black points to the full
     * available quantum range. The parameters provided represent the black, mid, and white points. The black point
     * specifies the darkest color in the image. Colors darker than the black point are set to zero. Mid-point specifies
     * a gamma correction to apply to the image. White point specifies the lightest color in the image. Colors brighter
     * than the white point are set to the maximum quantum value.
     *
     * Black and white points are multiplied with `QuantumRange` to decrease dependencies on the end user.
     *
     * @param blackPoint
     * @param gamma
     * @param whitePoint
     */
    @Throws(MagickWandException::class)
    external fun levelImage(blackPoint: Double, gamma: Double, whitePoint: Double)

    /**
     * Extends the image as defined by the geometry, gravity, and wand background color. Set the (x,y) offset of the
     * geometry to move the original wand relative to the extended wand.
     *
     * @param width The region width.
     * @param height The region height.
     * @param x The region x offset.
     * @param y The region y offset.
     */
    @Throws(MagickWandException::class)
    external fun extentImage(width: ULong, height: ULong, x: Long, y: Long)

    /**
     * Adds or removes an ICC, IPTC, or generic profile from an image. If the profile is NULL, it is removed from the
     * image otherwise added. Use a name of '*' and a profile of NULL to remove all profiles from the image.
     *
     * @param name The name of the profile. '*' to select all
     * @param profile The data to add/remove to the profile. NULL will remove the profile.
     */
    @Throws(MagickWandException::class)
    external fun profileImage(name: String, profile: UByteArray?)

    /**
     * Creates a vertical mirror image by reflecting the pixels around the central x-axis.
     */
    @Throws(MagickWandException::class)
    external fun flipImage()

    /**
     * Negates the colors in the reference image. The Grayscale option means that only grayscale values within the image are negated.
     */
    @Throws(MagickWandException::class)
    external fun negateImage()

    /**
     * Creates a horizontal mirror image by reflecting the pixels around the central y-axis.
     */
    @Throws(MagickWandException::class)
    external fun flopImage()

    /**
     * Blurs an image. We convolve the image with a gaussian operator of the given radius and standard deviation (sigma).
     * For reasonable results, the radius should be larger than sigma. Use a radius of 0 and `blurImage()` selects a
     * suitable radius for you.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation in pixels.
     */
    @Throws(MagickWandException::class)
    external fun blurImage(radius: Double, sigma: Double)

    /**
     * Blurs an image. We convolve the image with a Gaussian operator of the given radius and standard deviation (sigma).
     * For reasonable results, the radius should be larger than sigma. Use a radius of 0 and `gaussianBlurImage()`
     * selects a suitable radius for you.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Gaussian, in pixels.
     */
    @Throws(MagickWandException::class)
    external fun gaussianBlurImage(radius: Double, sigma: Double)

    /**
     * Adaptively resize the currently selected image.
     *
     * @param width The width in pixels.
     * @param height The height in pixels.
     */
    @Throws(MagickWandException::class)
    external fun adaptiveResizeImage(width: ULong, height: ULong)

    /**
     * Rotate the currently selected image by the given number of degrees,
     * filling any empty space with the background color of a given PixelWand.
     *
     * @param background The background pixel wand.
     * @param degrees The number of degrees to rotate the image.
     */
    @Throws(MagickWandException::class)
    external fun rotateImage(background: PixelWand, degrees: Double)

    /**
     * Trim the image removing the backround color from the edges.
     *
     * @param fuzz By default, target must match a particular pixel color exactly. However, in many cases two colors may
     *             differ by a small amount. The fuzz member of image defines how much tolerance is acceptable to
     *             consider two colors as the same. For example, set fuzz to 10 and the color red at intensities of 100
     *             and 102 respectively are now interpreted as the same color for the purposes of the floodfill.
     */
    @Throws(MagickWandException::class)
    external fun trimImage(fuzz: Double)

    /**
     * Retrieve the width of the image.
     */
    @Throws(MagickWandException::class)
    external fun getImageWidth(): ULong

    /**
     * Retrieve the height of the image.
     */
    @Throws(MagickWandException::class)
    external fun getImageHeight(): ULong

    /**
     * Retrieve the page geometry (width, height, x offset, y offset) of the image.
     */
    @Throws(MagickWandException::class)
    external fun getImagePage(): PageGeometry

    /**
     * Reset the Wand page canvas and position.
     */
    @Throws(MagickWandException::class)
    external fun resetImagePage(pageGeometry: String)

    /**
     * Retrieve the named image property value.
     *
     * @param name The property name to retrieve.
     */
    @Throws(MagickWandException::class)
    external fun getImageProperty(name: String): String

    /**
     * Set the named image property.
     *
     * @param name The name of the property to set.
     * @param value The value of the property to set.
     */
    @Throws(MagickWandException::class)
    external fun setImageProperty(name: String, value: String)

    /**
     * Returns a `PixelWand` instance for the pixel specified by [x] and [y] offsets.
     */
    @Throws(MagickWandException::class)
    external fun getImagePixelColor(x: Long, y: Long): PixelWand?

    /**
     * Sets the image sampling factors.
     *
     * @param samplingFactors An array of floats representing the sampling factor for each color component (in RGB order).
     */
    @Throws(MagickWandException::class)
    external fun setSamplingFactors(samplingFactors: DoubleArray)

    /**
     * Returns the image histogram as a List of `PixelWand` instances for every unique color.
     */
    @Throws(MagickWandException::class)
    external fun getImageHistogram(): List<PixelWand>?

    /**
     * Sharpens an image. We convolve the image with a Gaussian operator of the
     * given [radius] and standard deviation ([sigma]). For reasonable results, the
     * [radius] should be larger than [sigma]. Use a [radius] of 0 and `sharpenImage()`
     * selects a suitable [radius] for you.
     *
     * @param radius The radius of the Gaussian, in pixels, not counting the center pixel.
     * @param sigma The standard deviation of the Gaussian, in pixels.
     */
    @Throws(MagickWandException::class)
    external fun sharpenImage(radius: Double, sigma: Double)

    /**
     * Set the [background] color.
     */
    @Throws(MagickWandException::class)
    external fun setBackgroundColor(background: PixelWand)

    /**
     * Set the image [background] color.
     */
    @Throws(MagickWandException::class)
    external fun setImageBackgroundColor(background: PixelWand)

    /**
     * Returns the image resolution (horizontal resolution, vertical resolution).
     */
    @Throws(MagickWandException::class)
    external fun getImageResolution(): Resolution

    /**
     * Sets the image (horizontal) [x] and (vertical) [y] resolution.
     */
    @Throws(MagickWandException::class)
    external fun setImageResolution(x: Double, y: Double)

    /**
     * Sets the wand (horizontal) [x] and (vertical) [y] resolution.
     */
    @Throws(MagickWandException::class)
    external fun setResolution(x: Double, y: Double)

    /**
     * Applies a special effect to the image, similar to the effect achieved in a photo darkroom by sepia toning.
     * [threshold] ranges from 0 to QuantumRange and is a measure of the extent of the sepia toning. A [threshold] of 80
     * is a good starting point for a reasonable tone.
     *
     * @param threshold Define the extent of the sepia toning.
     */
    @Throws(MagickWandException::class)
    external fun sepiaToneImage(threshold: Double)

    /**
     * Extracts pixel data from an image and returns it to you as a UByteArray.
     *
     * @param x Defines the x perimeter of a region of pixels you want to extract.
     * @param y Defines the y perimeter of a region of pixels you want to extract.
     * @param width The width of the region of pixels you want to extract.
     * @param height The height of the region of pixels you want to extract.
     * @param map This string reflects the expected ordering of the pixel array. It can be any combination or order of
     *            R = red, G = green, B = blue, A = alpha (0 is transparent), O = alpha (0 is opaque), C = cyan,
     *            Y = yellow, M = magenta, K = black, I = intensity (for grayscale), P = pad.
     */
    @Throws(MagickWandException::class)
    external fun exportImagePixels(
        x: Long,
        y: Long,
        width: ULong,
        height: ULong,
        map: String
    ): UByteArray?

    /**
     * Resize the image to the specified [width] and [height], using the
     * specified [filter] type.
     */
    @Throws(MagickWandException::class)
    external fun resizeImage(width: ULong, height: ULong, filter: FilterType)

    /**
     * Extract a region of the image. The [width] and [height] is used as the size
     * of the region. [x] and [y] is the offset.
     */
    @Throws(MagickWandException::class)
    external fun cropImage(
        width: ULong,
        height: ULong,
        x: Long,
        y: Long,
    )

    /**
     * Sample the image to the target resolution
     *
     * This is incredibly fast, as it does 1-1 pixel mapping for downscales, and box filtering for
     * upscales.
     */
    @Throws(MagickWandException::class)
    external fun sampleImage(width: ULong, height: ULong)

    /**
     * Resample the image to the specified (horizontal) [x] and (vertical) [y] resolution, using the
     * specified [filter] type.
     */
    @Throws(MagickWandException::class)
    external fun resampleImage(
        x: Double,
        y: Double,
        filter: FilterType
    )

    /**
     * Rescale the image using seam carving algorithm.
     *
     * @param width The width of the scaled image.
     * @param height The height of the scaled image.
     * @param deltaX Maximum seam transversal step (0 means straight seams).
     * @param rigidity Introduce a bias for non-straight seams (typically 0).
     */
    @Throws(MagickWandException::class)
    external fun liquidRescaleImage(width: ULong, height: ULong, deltaX: Double, rigidity: Double)

    /**
     * Implodes the image towards the center by the specified percentage.
     *
     * @param amount The extent of the implosion.
     * @param method THe pixel interpolation method.
     */
    @Throws(MagickWandException::class)
    external fun implodeImage(amount: Double, method: PixelInterpolateMethod)

    /**
     * Resize the image to fit within the given dimensions, maintaining
     * the current aspect ratio.
     */
    @Throws(MagickWandException::class)
    external fun fit(width: ULong, height: ULong)

    /**
     * Detect if the loaded image is not in top-left orientation, and
     * hence should be "auto" oriented, so it is suitable for viewing.
     */
    @Throws(MagickWandException::class)
    external fun requiresOrientation(): Boolean

    /**
     * Automatically adjusts the loaded image so that its orientation is
     * suitable for viewing (i.e. top-left orientation).
     *
     * Returns `true` if successful or `false` if an error occurred.
     */
    @Throws(MagickWandException::class)
    external fun autoOrient(): Boolean

    /**
     * Write the current image to the provided [path].
     */
    @Throws(MagickWandException::class)
    external fun writeImage(path: String)

    /**
     * Write the image in the desired format to a new blob.
     *
     * @param format Any ImageMagick supported image format (e.g. GIF, JPEG, PNG, etc.).
     */
    @Throws(MagickWandException::class)
    external fun writeImageBlob(format: String): UByteArray?

    /**
     * Write the images in the desired format to a new blob.
     *
     * @param format Any ImageMagick supported image format (e.g. GIF, JPEG, PNG, etc.).
     */
    @Throws(MagickWandException::class)
    external fun writeImagesBlob(format: String): UByteArray?

    /**
     * Gets the number of unique colors in the image.
     */
    @Throws(MagickWandException::class)
    external fun getImageColors(): ULong

    /**
     * The filename associated with an image sequence.
     */
    var filename: String
        get() = magickGetFilename()
        set(value) = magickSetFilename(value)
    @Throws(MagickWandException::class)
    private external fun magickGetFilename(): String
    @Throws(MagickWandException::class)
    private external fun magickSetFilename(filename: String)

    /**
     * The font associated with the MagickWand.
     */
    var font: String
        get() = magickGetFont()
        set(value) = magickSetFont(value)
    @Throws(MagickWandException::class)
    private external fun magickGetFont(): String
    @Throws(MagickWandException::class)
    private external fun magickSetFont(font: String)

    /**
     * The format of the magick wand.
     */
    var format: String
        get() = magickGetFormat()
        set(value) = magickSetFormat(value)
    @Throws(MagickWandException::class)
    private external fun magickGetFormat(): String
    @Throws(MagickWandException::class)
    private external fun magickSetFormat(format: String)

    /**
     * The filename of a particular image in a sequence.
     */
    var imageFilename: String
        get() = magickGetImageFilename()
        set(value) = magickSetImageFilename(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageFilename(): String
    @Throws(MagickWandException::class)
    private external fun magickSetImageFilename(filename: String)

    /**
     * The format of a particular image in a sequence.
     */
    var imageFormat: String
        get() = magickGetImageFormat()
        set(value) = magickSetImageFormat(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageFormat(): String
    @Throws(MagickWandException::class)
    private external fun magickSetImageFormat(format: String)

    /**
     * The wand colorspace type.
     */
    var colorspace: ColorspaceType
        get() = magickGetColorspace()
        set(value) = magickSetColorspace(value)
    @Throws(MagickWandException::class)
    private external fun magickGetColorspace(): ColorspaceType
    @Throws(MagickWandException::class)
    private external fun magickSetColorspace(colorspace: ColorspaceType)

    /**
     * The wand compression type.
     */
    var compression: CompressionType
        get() = magickGetCompression()
        set(value) = magickSetCompression(value)
    @Throws(MagickWandException::class)
    private external fun magickGetCompression(): CompressionType
    @Throws(MagickWandException::class)
    private external fun magickSetCompression(compression: CompressionType)

    /**
     * The wand compression quality.
     */
    var compressionQuality: ULong
        get() = magickGetCompressionQuality()
        set(value) = magickSetCompressionQuality(value)
    @Throws(MagickWandException::class)
    private external fun magickGetCompressionQuality(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetCompressionQuality(quality: ULong)

    /**
     * The gravity type.
     */
    var gravity: GravityType
        get() = magickGetGravity()
        set(value) = magickSetGravity(value)
    @Throws(MagickWandException::class)
    private external fun magickGetGravity(): GravityType
    @Throws(MagickWandException::class)
    private external fun magickSetGravity(gravity: GravityType)

    /**
     * The image colorspace. Setting does not modify the image data.
     */
    var imageColorspace: ColorspaceType
        get() = magickGetImageColorspace()
        set(value) = magickSetImageColorspace(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageColorspace(): ColorspaceType
    @Throws(MagickWandException::class)
    private external fun magickSetImageColorspace(colorspace: ColorspaceType)

    /**
     * The image composite operator, useful for specifying how to composite the image thumbnail when using the
     * `MagickMontageImage()` method.
     */
    var imageCompose: CompositeOperator
        get() = magickGetImageCompose()
        set(value) = magickSetImageCompose(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageCompose(): CompositeOperator
    @Throws(MagickWandException::class)
    private external fun magickSetImageCompose(compose: CompositeOperator)

    /**
     * The image compression.
     */
    var imageCompression: CompressionType
        get() = magickGetImageCompression()
        set(value) = magickSetImageCompression(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageCompression(): CompressionType
    @Throws(MagickWandException::class)
    private external fun magickSetImageCompression(compression: CompressionType)

    /**
     * The image compression quality.
     */
    var imageCompressionQuality: ULong
        get() = magickGetImageCompressionQuality()
        set(value) = magickSetImageCompressionQuality(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageCompressionQuality(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetImageCompressionQuality(quality: ULong)

    /**
     * The image delay.
     */
    var imageDelay: ULong
        get() = magickGetImageDelay()
        set(value) = magickSetImageDelay(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageDelay(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetImageDelay(delay: ULong)

    /**
     * The image depth.
     */
    var imageDepth: ULong
        get() = magickGetImageDepth()
        set(value) = magickSetImageDepth(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageDepth(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetImageDepth(depth: ULong)

    /**
     * The image disposal method.
     */
    var imageDispose: DisposeType
        get() = magickGetImageDispose()
        set(value) = magickSetImageDispose(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageDispose(): DisposeType
    @Throws(MagickWandException::class)
    private external fun magickSetImageDispose(dispose: DisposeType)

    /**
     * The image endian method.
     */
    var imageEndian: EndianType
        get() = magickGetImageEndian()
        set(value) = magickSetImageEndian(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageEndian(): EndianType
    @Throws(MagickWandException::class)
    private external fun magickSetImageEndian(imageEndian: EndianType)

    /**
     * The image fuzz.
     */
    var imageFuzz: Double
        get() = magickGetImageFuzz()
        set(value) = magickSetImageFuzz(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageFuzz(): Double
    @Throws(MagickWandException::class)
    private external fun magickSetImageFuzz(fuzz: Double)

    /**
     *
     */
    var imageGamma: Double
        get() = magickGetImageGamma()
        set(value) = magickSetImageGamma(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageGamma(): Double
    @Throws(MagickWandException::class)
    private external fun magickSetImageGamma(gamma: Double)

    /**
     * The image gravity type.
     */
    var imageGravity: GravityType
        get() = magickGetImageGravity()
        set(value) = magickSetImageGravity(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageGravity(): GravityType
    @Throws(MagickWandException::class)
    private external fun magickSetImageGravity(gravity: GravityType)

    /**
     * The image interlace scheme.
     */
    var imageInterlaceScheme: InterlaceType
        get() = magickGetImageInterlaceScheme()
        set(value) = magickSetImageInterlaceScheme(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageInterlaceScheme(): InterlaceType
    @Throws(MagickWandException::class)
    private external fun magickSetImageInterlaceScheme(imageInterlaceScheme: InterlaceType)

    /**
     * The interpolation method for the specified image.
     */
    var imageInterpolateMethod: PixelInterpolateMethod
        get() = magickGetImageInterpolateMethod()
        set(value) = magickSetImageInterpolateMethod(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageInterpolateMethod(): PixelInterpolateMethod
    @Throws(MagickWandException::class)
    private external fun magickSetImageInterpolateMethod(method: PixelInterpolateMethod)

    /**
     * The image iterations.
     * The image delay is set in 1/100th of a second.
     */
    var imageIterations: ULong
        get() = magickGetImageIterations()
        set(value) = magickSetImageIterations(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageIterations(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetImageIterations(delay: ULong)

    /**
     * The image orientaiton.
     */
    var imageOrientation: OrientationType
        get() = magickGetImageOrientation()
        set(value) = magickSetImageOrientation(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageOrientation(): OrientationType
    @Throws(MagickWandException::class)
    private external fun magickSetImageOrientation(orientation: OrientationType)

    /**
     * Image rendering intent.
     */
    var imageRenderingIntent: RenderingIntent
        get() = magickGetImageRenderingIntent()
        set(value) = magickSetImageRenderingIntent(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageRenderingIntent(): RenderingIntent
    @Throws(MagickWandException::class)
    private external fun magickSetImageRenderingIntent(renderingIntent: RenderingIntent)

    /**
     * The image scene.
     */
    var imageScene: ULong
        get() = magickGetImageScene()
        set(value) = magickSetImageScene(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageScene(): ULong
    @Throws(MagickWandException::class)
    private external fun magickSetImageScene(delay: ULong)

    /**
     * The potential image type.
     */
    var imageType: ImageType
        get() = magickGetImageType()
        set(value) = magickSetImageType(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageType(): ImageType
    @Throws(MagickWandException::class)
    private external fun magickSetImageType(imageType: ImageType)

    /**
     * The image units of resolution.
     */
    var imageUnits: ResolutionType
        get() = magickGetImageUnits()
        set(value) = magickSetImageUnits(value)
    @Throws(MagickWandException::class)
    private external fun magickGetImageUnits(): ResolutionType
    @Throws(MagickWandException::class)
    private external fun magickSetImageUnits(units: ResolutionType)

    /**
     * The image interlace scheme.
     */
    var interlaceScheme: InterlaceType
        get() = magickGetInterlaceScheme()
        set(value) = magickSetInterlaceScheme(value)
    @Throws(MagickWandException::class)
    private external fun magickGetInterlaceScheme(): InterlaceType
    @Throws(MagickWandException::class)
    private external fun magickSetInterlaceScheme(interlaceScheme: InterlaceType)

    /**
     * The image interpolate pixel method.
     */
    var interpolateMethod: PixelInterpolateMethod
        get() = magickGetInterpolateMethod()
        set(value) = magickSetInterpolateMethod(value)
    @Throws(MagickWandException::class)
    private external fun magickGetInterpolateMethod(): PixelInterpolateMethod
    @Throws(MagickWandException::class)
    private external fun magickSetInterpolateMethod(method: PixelInterpolateMethod)

    /**
     * The position of the iterator in the image list.
     *
     * Also, you can set the iterator to the given position in the image list specified with the index parameter. A zero
     * index will set the first image as current, and so on. Negative indexes can be used to specify an image relative
     * to the end of the images in the wand, with -1 being the last image in the wand.
     *
     * If the index is invalid (range too large for number of images in wand) the function will return MagickFalse, but
     * no 'exception' will be raised, as it is not actually an error. In that case the current image will not change.
     *
     * After using any images added to the wand using `addImage()` or `readImage()` will be added after the image
     * indexed, regardless of if a zero (first image in list) or negative index (from end) is used.
     *
     * Jumping to index 0 is similar to `resetIterator()` but differs in how `nextImage()` behaves afterward.
     */
    var iteratorIndex: Long
        get() = magickGetIteratorIndex()
        set(value) = magickSetIteratorIndex(value)
    @Throws(MagickWandException::class)
    private external fun magickGetIteratorIndex(): Long
    @Throws(MagickWandException::class)
    private external fun magickSetIteratorIndex(index: Long)

    /**
     * Wand orientation type.
     */
    var orientation: OrientationType
        get() = magickGetOrientation()
        set(value) = magickSetOrientation(value)
    @Throws(MagickWandException::class)
    private external fun magickGetOrientation(): OrientationType
    @Throws(MagickWandException::class)
    private external fun magickSetOrientation(orientation: OrientationType)

    /**
     * The font pointsize associated with the MagickWand.
     */
    var pointsize: Double
        get() = magickGetPointsize()
        set(value) = magickSetPointsize(value)
    @Throws(MagickWandException::class)
    private external fun magickGetPointsize(): Double
    @Throws(MagickWandException::class)
    private external fun magickSetPointsize(pointsize: Double)

    /**
     * The magick wand image type attribute.
     */
    var type: ImageType
        get() = magickGetType()
        set(value) = magickSetType(value)
    @Throws(MagickWandException::class)
    private external fun magickGetType(): ImageType
    @Throws(MagickWandException::class)
    private external fun magickSetType(type: ImageType)
}
