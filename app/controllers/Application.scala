package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import views._

import models._



object Application extends Controller {
    val toppingSeq = Array("Pepperoni", "Cheese", "Anchovies")
    
    val signupForm = Form(
        mapping(
        "username" -> nonEmptyText,
        "password" -> nonEmptyText,
        "toppings" -> list(number)
        )(User.apply)(User.unapply)
    )
    
    /*
    val signupForm = Form(
        tuple(
          "username" -> text,
          "password" -> text,
          "toppings" -> list(number)
            ) verifying ("Inputting Data", result => result match {
            case (username, password, toppings) => insert(username, password, toppings)
            })
        )
    */
    /*
    val loginForm = Form(
      
        mapping(
        "username" -> nonEmptyText,
        "password" -> nonEmptyText
        )(UserData.apply)(UserData.unapply)
    //Do I need the apply/ Unapply part?
    )
*/
    def index = Action {
        Ok(html.index())
    }

    def login = Action {
        Ok(html.login(signupForm))
    }  
    
    def loginSubmit = Action {
        Ok(html.index())
        //make this do something
    }

    def signup = Action {
        Ok(html.signup(signupForm, toppingSeq))
    }

    def signupSubmit = Action { 
        implicit request =>
        signupForm.bindFromRequest.fold(
            errors => BadRequest(html.signup(errors, toppingSeq)),
            user => {
                User.create(user); 
                Ok(html.index())
        })
    //    println("hi")
        //need to actually map the user to somewhere
    }

    def noPizza = Action {
       Ok(html.noPizza())
    }
}
