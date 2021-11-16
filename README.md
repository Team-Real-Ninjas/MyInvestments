# MyInvestments
## Table Of Contents
1. [Overview](#Overview)
2. [Product Spec](#Product-Spec)
3. [Wireframes](#Wireframes)


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
- User should be able to log in using secure password and email
- User should be able to interact(see specific prices for the points on the graph) and 
- User should be able to input investments into their portfolio
- User should be able to view portfolio history.
- User should be able to make blog posts(Given Template) and save them to the blog tab

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

## Schema
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
   | Admin         | Boolean  | determines if user is an admin or not |
   

#### Investments

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | investmentId  | String   | unique id for the investment (default field) |
   | Type          | String   | type of investment |
   | comment       | String   | detail about type of investment |
   | purchaseAmount| Float    | price of investment |
   | stockAmount   | Number   | number of likes for the post |
   | createdAt     | DateTime | date when investment is created (default field) |
   | updatedAt     | DateTime | date when investment is last updated (default field) |

#### Posts

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | postId        | String   | unique id for the post (default field) |
   | contents      | String   | contents of post |
   | likes         | Number   | number of likes on post |
   | tags          | Array    | tags associated with post |
   | showComments  | Bool     | decides whether to display contents or not |
   | title         | String   | title of post |
   | user          | Pointer to User | user associated with post |
   | createdAt     | DateTime | date when post is created (default field) |
   | updatedAt     | DateTime | date when post is last updated (default field) |

#### Comments

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | commentId     | String   | unique id for the comment post (default field) |
   | contents      | String   | contents of comment |
   | likes         | Number   | number of likes on post |
   | user          | Pointer to User | user associated with comment |
   | post          | Pointer to Post | post associated with user |
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
