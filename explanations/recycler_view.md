#  Recycler view
 - used to efficiently display large sets of data which 
    1. improves app responsiveness 
    2. reduces power consumption while using your app
 - recycles individual elements, when an item scrolls off the screen, ```RecyclerView``` doesn't  destroy its view, instead ```RecyclerView``` reuses the view for new items that have scrolled onscreen
 - client has to:
    1. supply the data
    2. define how each item looks

## Key classes
 - ```RecyclerView```: is inherits ViewGroup(just like ConstraintLayout and other layouts) - it holds views which represents data, event thou it inherits ViewGroup you can look at it as a View item in some layout
 - ```ViewHolder```: each individual element can be represented by one ```ViewHolder```(usually we define ```ViewHolder``` per element type), when the view holder is crated it doesn't have any assotiated data with it
 - ```RecyclerView.Adapter```: binds elements to ViewHolders
 - ```Layout manager```: arranges ```ViewHolders``` in ```RecyclerView```
 


## View holder
 - view holder is wrapper around View that contains layout for induvidual item in the list
    ```
    class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {}
    ```
 - ussaly we define bind method which takes model as a parameter and binds it to View
e.g. 
    ```
    class ShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.showCardTitleTextView)
        private val image: ImageView = view.findViewById(R.id.showCardImageImageView)

        fun bind(show: ShowsUi) {
            title.text = itemView.context.getString(show.title)
            image.setImageResource(show.image)
        }
    }
    ```
## Adapter
 - ```RecyclerView``` talks to ```Adapter``` to create or bind with data specific ```Viewholder```
 - when implementing ```Adapter``` there are 3 methods you need to define:
    1.  method which will create ```ViewHolder``` based on given ```viewType```
        ```
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
        ```
    2. method that gives number of items which will be displayed in ```RecyclerView```
        ```
        public abstract int getItemCount();
        ```
    3. method that binds specific ```ViewHolder``` with data 
        ```
        public abstract void onBindViewHolder(@NonNull VH holder, int position);
        ```
    - position - parametar which tells you which item it wants to represent in given holder
    
    e.g.
    ```
    class ShowsAdapter(
        private val shows: List<ShowsUi>
    ) : RecyclerView.Adapter<ShowViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ShowViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.show_card, parent, false)
            )

        override fun getItemCount() = shows.size

        override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
            holder.bind(shows[position])
        }
    }
    ```
    
## RecyclerView
 - to work you need to give him instance of
    1. ```Adapter```
    2. ```LayoutManager```
 - e.g.
    ```
    showsRecyclerView.apply {
        layoutManager = LinearLayoutManager(this@ShowsActivity)
        adapter = ShowsAdapter(createShowUiList())
    }
    ```
 - additionally you can give him some ```Decorator``` to decorate your ```ViewHolders```(mostly used adding custom paddings on ```ViewHolders```)
 - attributes of xml: 
    1. ```clipToPadding``` - if false makes it possible for views to draw on ```RecyclerView``` padded area (useful for Card shadow)
    