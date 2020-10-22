# Notes on this project

### Problems
- `Rounds` only has one radius, but `Ellipse` needs two in order to function properly
    *Note:* I solved this by adding two new properties (`r1` and `r2`) to `Ellipse`, and setting the parent `myRadius` property to the average.
- Some multiple inheritance problems:
    - Rectangle is both an (isosceles) Trapezoid and a Parallelogram
    - Square is a Rectangle, a Rhombus and an `Agon`[^1]
    - Equilateral triangle is a Triangle and and `Agon`[^1]
- I wasn't sure what to do with invalid input, and java won't let me use a variable if it might not have been defined, so I looked at your code, found a place where you had a default section in a switch statement, and just copied that code into everywhere.
- I didn't know what to do with the largest/smallest polygon once I found it, I just guessed
- `Client.addPolygonToEndOfList` doesn't add a polygon to the end of the list, it adds one to the index before the last shape. This is your code, not mine.

### Questions
- Do we need enough information to theoretically draw the polygon, or just to find it's area?
- I assume `Agon` is regular polygons, based on the area formula it has, is this correct?

### Other Notes
- A trapezium is just called a trapezoid in American/Canadian English, and what you called a Trapezoid is an Isosceles trapezoid
- There is no Kite class
- Rounds *aren't* polygons
- You *can* implement the raw interface `Comparable` for `Polygon_Comparable`, and just use unchecked casting.

[^1] All the `Agon`s are regular polygons, so I assume that's what you meant by Agon, but I don't *know* that.