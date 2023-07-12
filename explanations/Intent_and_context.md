
## Context
 - interface to global information about an application environment.
 - abstract class whose **implementation is provided by the Android system**
 - It allows access to 
      1. application-specific resources and classes
      2. up-calls for application-level operations such as launching activities and broadcasting receiving intents, etc.
### Usage
 - In this chapter will will look at some `Context` uses.
    1. access application-specific resource:
            ```
            public abstract Resources getResources()
            ```
            - returns a resource instance for the **application package**
                - `Resournce` - class for accessing an application's resources, it has method like:
                    1 .getColor
                    2. getString
                    3. getLayout...


### Usage
In this chapter will will look at some ```Context``` uses
   1. access application-specific resource:
      ```
      public abstract Resources getResources()
      ```
	     - returns a resource instance for the **application package**
        - `Resournce` - class for accessing an application's resources, it has method like:
	            1 .getColor
                2. getString
                3. getLayout...
   2. starting activity
      ```
      public abstract void startActivity(@RequiresPermission Intent intent);
      ```

## Intent
 - is a messaging object you can use to request an action from another app component(e.g. Activity)
 - 2 types:
	 1. explicit - targeting specific app component(e.g. Activity)  
	 2. implicit - represents general action to perform, which can handle any app component with right intent filter
- you can define data within your intent, we can abstract instruction data to:
	  1. class
	  2. data
	  3. action
	  4. category
	  5. extended data

### Creating explicit intents:
 - for basic call(without addition extended data) we can call:
	 1. Set activity we want to start SomeActivity with Intent constructor:
	       ```Intent(context, SomeActivity::class:java)```
	2. Set activity we want after Intent object is created
	      ```
	      val intent = Intent(contex)
		  intent.setAction(ACTION_MAIN)
	      ```
	      or "Kotlin like":
	      ```
	      val intent = Intent(contex).apply {
	         acction = ACTION_MAIN
	    }
        ```
### Creating implicit intents:
 - for basic call(without addition extended data) we can call:
	```
	val intent = Intent(contex)
	intent.setClass(context, SomeActivity::class:java)
	```
	or "Kotlin like":
	```
	 val intent = Intent(contex).apply {
		  setClass(context, SomeActivity::class:java)
	 }
	```
- what happens in system when starting activity by implicit intent:
SLIKASLIKASLIKASLIKA 
- if you are defining activity to call by implicit intent you will probably need to define more informations


#### Setting more information for intent filtering:
 - data: ```String```  set scheme which intent filter can read and choose if its ok to start it's activity(e.g. https://google.com/,  file://yourDirectory/)
 - category: ```String```  set category so intent filter can filter by it(e.g.  CATEGORY_APP_EMAIL, CATEGORY_APP_MAPS)
 - action: ```String```  set action so intent filter can filter by it


#### Setting more data for app component:
 - you can store additional infromation by using ```Bundle```
 - think of bundle like map in which you can put values by String key
 - values must be implement interface ```Parcelable``` but for now think we can think that values are primitive types
- e.g. of use:
  1. creating Bundle object and populating it yourself: 
    ```
    val bundle = Bundle().apply {
	    
    }
	val intent = Intent(contex).apply {
       setClass(context, SomeActivity::class:java)
	}
	```
  2. populeting default(empty Bundle) in speficic Intent 
    ```
    val bundle = Bundle().apply {
	    
    }
	val intent = Intent(contex).apply {
       setClass(context, SomeActivity::class:java)
	}
	```

#### Intent filter
- it defines 0,1...more actions, categories, data one of which  implicit Intent must define in itself to pass the filter
- action tag defines action
- categoty tag defines category 
- data tag defines scheme
e.g.
SLIKE SLIKE

