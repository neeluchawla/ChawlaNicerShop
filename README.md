

Project Name : ChawlaNicerShop

Description: ChawlaNicerShop is an application build to help customer place orders for there favourite salads.

Pre-requisites: Open the app in android 3.4.1

Usage:

   1.The welcome page will the first page the load for the user.
   2. User need to click on click me button to navigate to the menu page.
   3. Select the quantity of the product.
   4. The price of individual salad is displayed in the card view without tax.And the total with and without tax is displayed on the top.
   5. Place order button will be enabled once any product is selected.
   6. Place order.


Menu used :
1. Option Menu 
    a. Used 3 item for this menu.
    - Two that will be shown as collapsed "Contact by phone and contact by email"
    - The third item is to calculate total excluding tax that can be used to see the price in real time
    
    Reason for choosing the option menu:
    
    These items are general item , not related to any particular product so these were better in the toolbar. Also by using contact by email and phone in collapse , the space in the toolbar is saved.
    
    
2. Pop up menu
   Pop up menu is used here to 
    - Share info with friends : for this project have shown using toast
    - More information : which is used to give additional information about product i.e whether the product is veg / gluten free .
    



Also have used additional features as mentioned below :

1. User cant click on cart icon or dollar sign in toolbar until user has selected min one product , and for this i have added a toast to display details.
2. Cart icon is disabled until one product is selected.
3. The alert dialog is used for delivery options.