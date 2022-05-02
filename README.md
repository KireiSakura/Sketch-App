# Sketch App

<b>OpenJDK version:</b> "11.0.8" 2020-07-14

<b>Gradle version:</b> "6.6.1"

<b>Sketch App:</b>

- The tool pallete consists of select tool, circle draw tool, rectangle draw tool, line draw tool, fill shape tool and eraser tool, in this order. When no shape is on the canvas, the select tool, the eraser tool and the fill shape tool are disabled.

- The properties pallete consists of two color pickers, one to select the shape color for circle and rectangle, and one to select the color for either a line shape or for the borders for circle and rectangle.

- There are three line thickness provided: thin, medium, thick

- There are three line styles provided: solid, dashed, dotted

- When you click on the select tool, all the elements of the properties tool will get disabled till you click on a shape to select it. When you click on a shape to select, the property tools will get enabled and they will reflect the properties of the shape.

- When you select a line with the select tool, the shape fill colorpicker is disabled, since line does not contain a shapefill(it contains a line color).

- If a shape with no border is selected with the select tool, then the line color color picker will have null value. The line thickness property and the line style property will be disabled, since the shape has no border. If you want to add a border to this shape while it is selected, you will first have to select a color from the line color colorpicker(since line color colorpicker cannot have null value to add border to a shape). So, once you select a color from the line color colorpicker. After that, the line thickness and line style properties will be unlocked(enabled), so that you can modify the thickness and style for the border.

<b>Indication that a shape is Selected:</b>

The shape which is selected by a select tool will have a black shadow on it to denote that it has been selected.

<b>Menu Options</b>

<b>Save and Load: </b>

- <b>Save: </b>Whenever you will save a canvas, a dialog will appear asking for a file name with which you want to save the file. You must provide a .txt file. After that your canvas will be saved in the filename provided.

- <b>Load: </b>Whenever you will load a canvas, first you will asked if you want to save the file in a dialog box. If you click no, the drawing will not be saved and we will proceed with the loading part. If you click on yes, you will get a dialog box asking for the filename with which you want to save the file(a .txt file) and after submitting that your will be saved.After that a file browser will open up, with starting destination of a3 folder. It will ask you to select a file. You must select a .txt file. After selecting the file, your canvas will be opened.

<b>New and Quit: </b>

- For New, first you will asked if you want to save the file in a dialog box. If you click no, the drawing will not be saved and we will proceed with the loading part. If you click on yes, you will get a dialog box asking for the filename with which you want to save the file(a .txt file) and after submitting that your will be saved. After that a blank canvas will open up.

- For exit, first you will asked if you want to save the file in a dialog box. If you click no, the drawing will not be saved and we will proceed with the loading part. If you click on yes, you will get a dialog box asking for the filename with which you want to save the file(a .txt file) and after submitting that your will be saved. After that, the system will exit.

-<b> Help:</b> The help menu will have a menu item about, clicking on which dialog will appear displaying my program(Computer Science), my name and my WATID.

<b>Window Resizing: </b>

- Minimum Window Size: 910x650
- Maximum Window Size: 1440x830

The properties pallete will require resizing and will resize as you resize the window.
