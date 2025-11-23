# Dynamic Sketch Element Loading System

## Overview

The Suspectra v2 project now supports **dynamic loading of sketch elements** from folders. This means you can add new hairstyles, eyes, noses, or any other sketch elements simply by dropping PNG files into the appropriate folders - **no code changes required!**

## How It Works

### Before (Hardcoded)
- Every sketch element (hair, eyes, etc.) was hardcoded in the FXML file
- Adding new elements required:
  1. Adding ImageView to FXML
  2. Updating controller code
  3. Adding event handlers
  4. Recompiling

### Now (Dynamic)
- Elements are automatically scanned from folders at startup
- Simply add PNG files to the appropriate folder
- The system automatically:
  - Creates ImageView elements
  - Sets up event handlers
  - Displays them in the UI
  - Makes them draggable on the canvas

## Folder Structure

Sketch elements are organized in the following structure:

```
src/suspectra/v2/elements/sketch elements/
├── hair/
│   ├── 01.png
│   ├── 02.png
│   ├── 13.png
│   ├── Group 101.png
│   └── ... (any PNG files)
├── head/
│   └── ... (PNG files)
├── eyes/
├── eyebrows/
├── nose/
├── lips/
├── mustach/
└── more/
```

## Adding New Elements

### Example: Adding New Hairstyles

1. **Add your images** to the folder:
   ```
   src/suspectra/v2/elements/sketch elements/hair/
   ```
   - Add files like `13.png`, `Group 101.png`, or any name you want
   - Files are automatically sorted alphabetically

2. **Run the application** - that's it!
   - New elements will appear in the hair selection panel
   - They'll be automatically numbered and laid out in a grid
   - Click any element to add it to the sketch canvas
   - Elements are draggable on the canvas

### Supported Element Types

- **hair** - Hairstyles
- **head** - Head shapes
- **eyes** - Eye shapes
- **eyebrows** - Eyebrow styles
- **nose** - Nose shapes
- **lips** - Lip shapes
- **mustach** - Mustache styles
- **more** - Additional elements

## Technical Details

### Files Changed/Created

1. **DynamicElementLoader.java** (NEW)
   - Utility class that scans folders for PNG files
   - Creates ImageView elements programmatically
   - Handles both development (file system) and JAR (packaged) modes

2. **DashboardController.java** (MODIFIED)
   - Added `loadElementsDynamically()` method
   - Updated `initialize()` to load elements at startup
   - All selection methods now handle dynamic elements
   - Reset function works with dynamic elements

### Key Methods

- `loadElementFiles()` - Scans folder and returns list of PNG files
- `loadElementType()` - Creates ImageViews for a specific element type
- `handleDynamicElementSelect()` - Handles selection of dynamically created elements
- `setupDragHandlers()` - Makes sketch elements draggable

## Backward Compatibility

The system is designed to work alongside existing hardcoded elements:
- Hardcoded element ImageViews are automatically removed when dynamic loading runs
- Hardcoded sketch ImageViews (in the canvas) are kept for compatibility
- Existing functionality remains intact

## Troubleshooting

### New elements not showing up?

1. **Check file location**: Ensure PNG files are in the correct folder
   ```
   src/suspectra/v2/elements/sketch elements/[element_type]/
   ```

2. **File format**: Make sure files are `.png` (lowercase extension)

3. **Rebuild**: After adding files, recompile the project:
   ```powershell
   javac -cp "javafx-sdk-17.0.2\lib\*;lib\*" --module-path "javafx-sdk-17.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web -d build src\suspectra\v2\*.java
   ```

4. **Resource loading**: If running from JAR, ensure resources are included in the JAR file

### Elements not appearing in correct order?

- Files are sorted alphabetically
- To control order, use numbered filenames: `01.png`, `02.png`, `13.png`, etc.
- Or use descriptive names: `Group 101.png`, `Group 102.png`

## Future Enhancements

Possible improvements:
- Support for subfolders within element types
- Custom ordering via configuration file
- Element metadata (names, descriptions)
- Preview thumbnails
- Element search/filtering

## Notes

- **Image naming**: Use descriptive names like `13.png` or `Group 101.png`
- **File count**: No limit on number of elements per type
- **Performance**: Loading happens once at startup
- **Grid layout**: Elements are displayed in a 3-column grid with automatic row wrapping

## Example Usage

To add a new hairstyle:
1. Create or find a PNG image (e.g., `13.png`)
2. Copy it to: `src/suspectra/v2/elements/sketch elements/hair/13.png`
3. Copy it to: `build/suspectra/v2/elements/sketch elements/hair/13.png` (after compiling)
4. Run the application
5. Click the hair icon in the toolbar
6. Your new hairstyle will appear in the selection panel!

---

**Enjoy your dynamic sketch element system!** 🎨

