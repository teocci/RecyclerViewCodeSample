## Android RecyclerView Examples

This repository contains examples for using the RecyclerView widget found in the Android Support Library.

### Disclaimer
This repository contains sample code intended to demonstrate the capabilities of the RecyclerView layout manager APIs. It is not intended to be used as-is in applications as a library dependency, and will not be maintained as such. Bug fix contributions are welcome, but issues and feature requests will not be addressed.

### Example Contents
The following bits can be found in the main sample application:

- Implementation of `LinearLayoutManager` and `GridLayoutManager` for vertical and horizontal scrolling.
- Custom ItemDecorations
    * `InsetDecoration` - Create an inset margin on all child views.
    * `DividerDecoration` - Create an inset margin and draw dividers below vertical child views.
    * `GridDividerDecoration` - Create an inset margin an draw dividers along grid lines
- Custom LayoutManager
    * `FixedGridLayoutManager` - Similar to `StaticGridLayoutManager`, but with a controllable column count.
 
The following examples are incubating on the `experimental` branch (these mostly work, if you feel like living dangerously):
- Custom LayoutManagers
    * `StaticGridLayoutManager` - 2D scrolling grid with variable column count based on data set. Window of visible (non-recycled) views is determined statically.
    * `DynamicGridLayoutManager` - 2D scrolling grid where window of visible views is determined dynamically. Results in fewer views in memory, but scrolling performance is questionable.
    
### Pre-requisites
    
- Android SDK 25
- Android Build Tools v25.0.2
- Android Support Repository

## License

The code supplied here is covered under the MIT Open Source License.