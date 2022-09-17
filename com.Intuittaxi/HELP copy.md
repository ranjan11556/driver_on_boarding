Driver On-Boarding Module:

FR:
1. Sign up as a driver eg: enter profile
2. Trigger OnBoarding
     A) Doc collection => BackGround Verification => Shipment of tracking devices => Educative/protocol/rule Course
3. Allow a driver to mark when they are ready to take a ride 

Additional :
1. Notification of each steps and progress
2. Fraud detection


APIs:
https://uber.com/
Localhoist:8082

1. POST : /login/
    BODY { login : mob no.  ,  password': '123456' }
2. POST : /logout/
     BODY { session : “ssvsvsvsvdkdfhdjksdhnjdhv’}

3. POST : /signup/
BODY {
               FirstName : “ranjan”
               LsatName : “Kumar”
               password. :  “kjhjkswhcjs”
	    Phone NO. : “951231”
	    City you’ll drive : “blr”
	    Invite Code(optional) : “XDBD45X” 
            }
4. POST : /upload/			+      PUT
BODY {
		name: “DL”
		file: “xfile”
            }
Response {
    200: ok
}

5.  GET : /startRide/


LLD:
MVC:
    client => waiter =>     chef        => freez.       => Ingredients
    UI.    =>  controller => services => DB(repo) => Models   

Entity:

Driver: {UUIDDriver, name, phone etc…, Finalstatus}


Document => {UUIDDriver, UUIDDoc ,  pic, DL, PAN Card, RC, Vechile permit}

// DocVerificationStatus =>{UUIDDriver, UUIDDoc , pic, DL, PAN Card, RC, Vechile permit}.    =>  {to be uploaded, verification in progress, verified}

// DrivingStatus : {NOT VERIFIED, ALLOWED, DRIVING, BLOCKED}


Controller:
AuthController.  +  ProfileController
DocUploadController

Services:
DocumentCollectionService
BackGroundVerificationservice
TrackingDeviceShipment

Notification
CustomerOnBoarding
 
Repository:
DriverRepo: 
DocumentRepo


Exceptions:

Logging:

Docitem {
	type name;
       String url;
       Status : 
	int number:
}

1. Chain of responsibility ?
2. pubSub 

TDD:
1. delete APIs
2. Get + Update APIs
3. StateMachine
4. TestCases
5. API tests



