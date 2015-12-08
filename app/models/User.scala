package models

case class User(username: String, password: String, toppings: List[Int])
object User {
	/*
	def insert(){
	}
	*/
  	def all(): List[User] = Nil
 	def create(user: User) {
		println(user.toString())
 	}
 	def delete(id: Long) {}
}