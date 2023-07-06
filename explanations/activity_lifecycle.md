# Activity lifecycle
- as a user navigates through, out of, and back to your app, the Activity instances in your app transition through different states in their lifecycle 
- Activity class provides callbacks for: 
	1. state changes
	2. events when system is creating, stopping, or resuming an activity or destroying the process the activity resides in 
- you can override those methods and provide your logic to:
	1. handle state changes (e.g. stop disconnect app from internet if app is not in foreground and connect when is in foreground)
	2. setting up the Activity (since Activity constructor is private)
- good implementation of those lifecycle callbacks will make your app avoid the following:
	1.  crashing if the user receives when an app is removed form foreground
	2.  consuming valuable system resources when the user is not actively using it (e.g. camera)
	3.  losing the user's progress if they leave your app and return to it at a later time
	4. crashing or losing the user's progress when the screen rotates between landscape and portrait orientation

## Activity-lifecycle concepts
- activity class provides a core set of six callbacks: 
	1. onCreate()
	2. onStart() 
	3. onStop()
	4. onPause()
	5. onDestroy()
- system invokes each of this callbacks when activity enters new state
- you can group those callbacks in 2 groups:
	1. creation (first three)
    2. destroying (last three)


  ![activity lifecycle](../explanations/res/drawables/activity_lifecycle.png)


### Lifecycle callbacks
In this section all lifecycle callbacks will be explained 

#### onCreate():
 - On activity creation, the activity enters the _Created state_. 
 - when activity enters CratedState system will call `onCreate()`
 - In the `onCreate()` method, perform basic application startup logic that happens only once for the entire life of the activity
 - in this method implement logic which needs to be run for set up only once(e.g. change color of status bar, inflating layout, get view objects which are shown on screen,...) 
    ```
    latient var button: Button
    
    override  fun onCreate(savedInstanceState:  Bundle?)  { 
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.RED
        setContentView(R.layout.activity_layout)
        button = findViewById(R.id.my_button)
    }  
    ```
 - after the `onCreate()` method finishes execution, the activity enters the _Started state_ and the system calls the `onStart()` and `onResume()` methods in quick succession.    
 - it receives the parameter `savedInstanceState`, which is a type of `Bundle` and it contains activity's previously saved state(e.g. some id which helps you get other information). If the activity has never existed before, the value of the `Bundle` object is `null`
 1. getting info from bundle object
  
	```
	override  fun onCreate(savedInstanceState:  Bundle?)  { 
		super.onCreate(savedInstanceState)
		userId = savedInstanceState?.getString(USER_ID)
	}	
	```
 2. saving info into bundle object
- this callback is called when activity will be temporarily destroyed(e.g. lack of memory for foreground app)
	```
	latient var button: Button

	override fun onSaveInstanceState(outState: Bundle?) {
	    super.onStart()
	    button.setOnClickListner(this)
    }
	```
#### onStart()
- when the activity enters the _Started state_, the system invokes `onStart()`
- this call makes the activity visible to the user as the app prepares for the activity to enter the foreground and become interactive.
- good use for this callback is setting listeners, since we know that layout is inflated and soon user will be able to press buttons
e.g.
	override fun onStart() {
        outState?.putString(GAME_STATE_KEY, gameState)
        super.onSaveInstanceState(outState)
    }
- once this callback finishes, the activity enters the _Resumed state_ and the system invokes the `onResume()` method.
#### onResume()
- when the activity enters the _Resumed state_, it comes to the foreground, and the system invokes the `onResume()` callback
- this is the state in which the app interacts with the user, and app will stay in this state until something happens to take focus away from the app, such as
    1. the device receiving a phone call
    2. user navigating to another activity
    3. device screen turning off
- when an interrupt event occurs, the activity enters the _Paused state_ and the system invokes the `onPause()` callback.
#### onPause()
 -  system calls this method as the first indication that the user is leaving your activity, but it is still visible if the user is in multi-window mode.
 -  though it does not always mean the activity is being destroyed, it indicates that the **activity is no longer in the foreground**, but it is still visible if the user is in multi-window mode
 -  use it to:
    1. to pause or adjust operations that can't continue, or might continue in moderation, while the Activity is in the _Paused state_, and that you expect to resume shortly.
    2. release system resources, handles to sensors (like GPS), or any resources that affect battery life while your activity is Paused and the user does not need them. (while your activity is using camera, no other activity can use camera 
- it's execution is very brief and **does not necessarily offer enough time to perform save**, we will use `onDestory` for that
- if the activity returns from the _Paused state_ to the _Resumed state_, the **system keeps the Activity instance resident in memory**, recalling that instance when the system invokes `onResume()`. In this scenario, you don’t need to re-initialize components created during any of the callback methods leading up to the _Resumed state_
#### onStop()
- when your activity is no longer visible to the user, it enters the _Stopped state_, and the system invokes the `onStop()` callback. This can occur when 
    1. a newly launched activity covers the entire screen. 
    2.  activity finishes running and is about to be terminated.  (`finish()`)
 - method usage:
      - stop UI intensive work(e.g. animation) [science when activity is _Pause state_ is still visible]
      - perform relatively CPU-intensive shutdown operations. (e.g. if you can't find a better time to save information to a local database
 - when your activity enters the _Stopped state_, the `Activity` object is kept in memory(it maintains all state and member information, but is not attached to the `window manager`. When the activity resumes, it recalls this information.)
 - system also keeps track of the current state for each `View` object in the layout, so if the user enters text into an `EditText` widget, that content is retained so you don't need to save and restore it.
 - From this state, the activity can:
    1. come back to interact with the user (calls `onRestart()`)
    2. finish running and go away. (calls `onDestroy()`)
### onDestroy()
 - called before the activity is destroyed, it can happen because of 2 things:
      1. activity is finishing (due to the user completely dismissing the activity or due to `finish()` being called on the activity)
      2. system is temporarily destroying the activity due to a configuration change, such as device rotation or entering multi-window mode.
 - it is called as the result of a configuration change, the system immediately creates a new activity instance and then calls `onCreate()` on that new instance in the new configuration.
 - The onDestroy() callback releases all resources not released by earlier callbacks, such as `onStop()` (e.g. object attributes contained in your activity)


	
