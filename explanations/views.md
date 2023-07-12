# Views


##### Listeners
 - view interface allows us to listen for changes in state of ```View``` object, this could be:
		 1. isClicked
		 2. isDraged
		 3. isFocused
		 4. ...
- they are based on Observer pattern, where ```View``` is subject, and most commonly ```Activity``` is observer
##### Listening view clicks
 - method to start listening clicks on ```View```(subject pattern - attach method):	
 ```setOnClickListener(@Nullable OnClickListener l)```

- interface which object type will be called to notify when ```View``` isClicked
	```interface
	public interface OnClickListener {  
	  void onClick(View v);  
	}```

e.g. of use:
```
   val lisener = object : OnClickListener {
        override fun onClick(v: View?) {
        // logic to do when View is clicked
      }
      someView.setOnClickListener(listener)
   } 
```
#### TextView
 - used to show uneditable text
 - as such it has custom attributes:
    1. ```text``` = text which will be shown on screen
    2. ```textSize```
    3. ```fontFamily``` = font
    4. ```textColor```
    5. ....

#### ImageView
 - used to show drawables/images
 - as such there are custom attributes:
   1. ```src``` = drawable which will be shown on screen
   2. ```scaleType``` = defines how image should be resized or moved to match the size of ImageView

#### TextInput
- is ```EditText``` from material library
- as such there are custom attributes:
    1. ```textCursorDrawable``` - make cursor defined drawable, you can add @null to set default drawable colored with text color
    2. inputType - defines user input type, can be:
          1. ```textPassword``` - makes that inputed text are starts
          2. ```text ```
          3. ....
##### Listening for change in text:
 - method to start listening clicks on View(subject pattern - attach method):	
 ```public void addTextChangedListener(TextWatcher watcher) ```
- interface which object type will be called to notify when text input is changed:
```
public interface TextWatcher extends NoCopySpan {
   public void beforeTextChanged(CharSequence s, int start,  int count, int after);
   public void onTextChanged(CharSequence s, int start, int before, int count);
   public void afterTextChanged(Editable s);
}
```
e.g. of use:
```
val listener = object : TextWatcher {
	override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
	override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable?) {}
}
inputText.addTextChangedListener(listener)
```
####  TextInputLayout
- used to decorate ```TextInput```, to which ```TextInput``` should be child of ```TextInputLayout```
- as such there are custom attributes:
	 1. ```hintTextColor``` - hint text color which is placed in TextInputLayout
	 2. ```hint``` - hint text
	 3. ```helperTextTextColor```
	 4. ```boxStrokeColor```
	 5. ```boxCornerRadiusTop/Bottom/Left/Right```
	 6. ```style``` - used to change apparence of a decorator, we can set it with our own style or pick style provided by material library



## Resources
- there is a directory called ```res``` in which we can define not mutable properties and values


### Selector
 - mehanizam defined in xml which chooses its value based on ```View's``` state
 - its used because some ```view's``` have only one entry point to set something with attribute, this way we can change given value when view state is changed
 
 - e.g.
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true" android:color="@color/text_pressed" />
    <item android:state_pressed="false" android:color="@color/text_normal" />
</selector>
```

### Fonts
- fonts can be imported to project by placing ```.ttf``` file in ```res/font```directory
