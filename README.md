# MyInvestments
## Table Of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)
4. [Schema](#Schema)


## Overview
### Description 
MyInvestments is a personal investment portfolio. It is a platform that allows for the monitoring of BTC(Bitcoin), ETH(Etherum), GLD(Gold), and SLVR(Silver) stocks in real-time. MyInvestments also allow users to input their investments and see them in graphical or listed form. Finally users can create posts in their Investment Blogs.

## App Evaluation
- **Category:** Personal Finance/ Blog
- **Story:** Analyzes users investment choices, and allows them to montior them in various ways. The user can then decide to create blog posts about their learnings and help retain knowledge.
- **Market:** Any individual that has investments could choose to use this app, and to keep it a secure, they will have a secure login.
- **Habit:** This app could be used as often or unoften as the user wanted depending on how deep they are into their personal finances, and what exactly they’re looking/monitoring for.
- **Scope:** First we would start with creating the home page so users can manage/monitor/and blog about the BTC, ETH, GLD, and SLVR stocks, then perhaps this could evolve into a news/portoflio platform this will broaden its usage. Large potential for use with local news sites(Direct users to their site), local blogs, and BTC/ETH Platforms.

## Product Spec

### 1. User Stories (Required and Optional)

### Required Must-have Stories
[x] User should be able to log in using secure password and email
- User should be able to interact(see specific prices for the points on the graph) and 
[x] User should be able to input investments into their portfolio
- User should be able to view portfolio history.
[x] User should be able to make blog posts(Given Template) and save them to the blog tab

### Optional Nice-to-have Stories
- User should be able to see their basic details
- User should be able to filter the stock graphs
- User should be able to filter their portfolio
- User should be able to view news stories from the web that contains info(tags) on:
        - Cryptocurrency
        - Sustainability
        - ESG
        - Sustainability investing
        - Sustainability goals
        - Major World News Stories
        - Developing cities
        - UN
 - User should be able to view posts from LinkedIn that contains info(tags) on:
 - User should be greeted with a brief welcome screen —“Hello (USER NAME), Welcome to your Investments"

### 2. Screens
- **Login** - Display username and password login.
- **Home Screen** - Displays current stock charts. Has push notifications about stock increases/decreases
- **Portfolio Screen** - Dsiplays user investments (chart + written format). Displays weekly report. Displays investment inputs.
- **Portfolio Blog Screen** - Displays Blog and Users can add posts and comments on a blog posts.
- **News Screen** **(Optional Story)** - Displays relevant news stories about stocks.
- **Account Screen** **(Optional Story)** - Displays basic user account information
- **Logout** - redirects to login screen.

### 3. Navigation

#### Tab Navigation (Menu to Screen)
- Stock Watcher (Monitor Stocks)
- Portfolio
- Blog
- Account
- Logout

### Optional:
- News
- Account Details

### Flow Navigation (Screen to Screen)
- **Log-in** --> Account creation if no login is available
- **Stock Watcher** --> Stock Charts(No Navigation) 
- **News** --> News Platforms
- **Portfolio** --> Investment Inputs
- **Blog** --> Text field to be modified.
- **Logout** --> Login Screen

# Wireframes
![Wireframe](https://user-images.githubusercontent.com/59378562/141025753-bcccc76b-b88c-496c-a02c-f8f53a8320ef.jpg)

## Digital Wireframes & Mockups
![Project](https://user-images.githubusercontent.com/59378562/141025638-99b0fc3e-630b-41c1-af31-f2135e72bb63.png)

## Interactive Wireframe
https://www.figma.com/proto/l9BzgvDghVJnCfdYsJzxHx/MyInvestSmart?node-id=19%3A88&scaling=min-zoom&page-id=0%3A1&starting-point-node-id=2%3A2

# Schema
### Models
#### User

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | userId        | String   | unique id for the user (default field) |
   | firstName     | String   | first name of user |
   | lastName      | String   | last name of user |
   | phoneNumber   | String   | phone number of user |
   | username      | String   | username of user |
   | password      | String   | password of user |
   | emailAddress  | String   | email address of user |
   | admin         | Boolean  | determines if user is an admin or not |
   

#### Investments

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | investmentId  | String   | unique id for the investment (default field) |
   | createdBy     | Refrence to USER | user associated with this Investment INPUT |
   | type          | String   | type of investment |
   | comment       | String   | detail about type of investment |
   | purchaseAmount| Float    | price of investment |
   | stockAmount   | Number   | price of stock at the time of investment |
   | createdAt     | DateTime | date when investment is created (default field) |
   | updatedAt     | DateTime | date when investment is last updated (default field) |

#### Posts

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | postId        | String   | unique id for the post (default field) |
   | author          | Refrence to USER | user associated with post |
   | contents      | String   | contents of post |
   | likes         | Number   | number of likes on post |
   | tags          | Array    | tags associated with post |
   | showComments  | Bool     | decides whether to display contents or not |
   | title         | String   | title of post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |

#### Comments

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | commentId     | String   | unique id for the comment post (default field) |
   | contents      | String   | contents of comment |
   | likes         | Number   | number of likes on post |
   | user          | Refrence to USER | user associated with comment |
   | post          | Refrence to POST | post associated with user |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |

#### Session

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | sessionId     | String   | unique id for the session (default field) |
   | sessionToken  | String   | unique id for the session token (default field) |
   | user          | Pointer to User | user associated with session |
   | restricted    | Bool     | determines if session is restricted or not |
   | installationId| String   | unique id for session installation (default field) |
   | createdWith   | Object   | action used to create session (i.e. login) |
   | createdAt     | DateTime | date when session is created (default field) |
   | updatedAt     | DateTime | date when session is last updated (default field) |
   | expiresAt     | DateTime | date when session expires (default field) |
   
## Networking
### List  of network requests by screen
- Portfolio Screen
	- (Read/GET)  Query all  investment posts where user is  the author
	- (Create/POST) Create a new investement post
	- (Update/PUT) Update investment posts

- Blog Screen
	- (Read/GET) Query all posts where user is author

```
	public Post getPostById(String id)throws ExecutionException, InterruptedException{
	    Post post = null;
	    //database connection object
	    Firestore db = FirestoreClient.getFirestore();
	    //retrieves a reference to the document(row) of the collection (table) with a specific id
	    DocumentReference postRef = db.collection("Post").document(id);

	    //ApiFuture allows us to make async calls to the database
	    ApiFuture<DocumentSnapshot> future = postRef.get();
	    //Retrieve document
	    DocumentSnapshot document = future.get();

		    //Convert JSON into Post class object
		    if(document.exists())
		    {
			UserService service = new UserService();

			DocumentReference userRef = (DocumentReference) document.get("author");
			ApiFuture<DocumentSnapshot> userQuery = userRef.get();
			DocumentSnapshot userDoc = userQuery.get();
			User user = userDoc.toObject(User.class);

			post =  new Post(document.getId(), document.getString("title") , document.getString("content"),
				(ArrayList<String>) document.get("tags"), document.getBoolean("showComments")
				, document.getLong("likes"), user);
		    }


	    return post;
	 }
```
	- (Create/POST) Create a new post
	
	
```      
    public String createPost(RestPost post) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> postRef = db.collection("Post").add(post);
        return postRef.get().getId();
    }
```	

	- (Create/POST) Create a new comment on a post
	- (Delete) Delete existing comment
```   
    public Boolean deleteComment(String  num) throws ExecutionException, InterruptedException{
        //database connection object
        Firestore db = FirestoreClient.getFirestore();
	    ApiFuture<WriteResult> writeResult = db.collection("Comment").document(num).delete();
	if( writeResult.get().getUpdateTime() != null)
        return true;
	
	     return false;
    }
``` 
- Account Screen
	- (Read/GET) Query user basic details (Stretch)**
	- (Update/PUT) Update user profile image (Strecth)**
        
```
     public RestUser updateUserProfileImage(String num) throws ExecutionException, InterruptedException {
        ObjectMapper mapObject = new ObjectMapper();
        Firestore db = FirestoreClient.getFirestore();

        DocumentReference userRef = db.collection("User").document(id)
	      DocumentSnapshot docs = (DocumentSnapshot) userRef.get().get();

        //ApiFuture allows uss to make async calls to database
        ApiFuture<QuerySnapshot> futurepNum = query.get();
        List<QueryDocumentSnapshot> docs = futurepNum.get().getDocuments();


        if(docs.size() > 0){
            DocumentReference doc = docs.get(0).getReference();
            //String refers to value name ... Object is what we are passing into it
            Map<String, Object> update = new HashMap<>();
            update.put("profileImage", user.getProfileImage());
 
            //Async Document Update
            ApiFuture<WriteResult> writeResult = doc.update(update);
        }
        return user;
     }

```       

   
### [IF EXISTS:] Existing API Endpoints
        
  # Polygon Stock API      
    
    - Base URL - (https://polygon.io/docs/getting-started)     

   | HTTP Verb     | EndPoint | Description |
   | ------------- | -------- | ------------|
   | GET           | v2/reference/news | Get the most recent news articles relating to a stock ticker symbol, including a summary of the article and a link to the original source. |
   | GET           | /v1/marketstatus/now | Get the current trading status of the exchanges and overall financial markets. |
   | GET           | /vX/reference/tickers/{ticker} |  Get a single ticker supported by Polygon.io. The response will have detailed information about the ticker and the company behind it. |
   | GET           | /v1/open-close/crypto/{from}/{to}/{date} | Get the open, close prices of a cryptocurrency symbol on a certain day. |

   
   
   ### Build Progress
   
   gifs/pictures unavailable, build still in progress
