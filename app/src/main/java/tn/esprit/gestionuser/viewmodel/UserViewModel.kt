package tn.esprit.gestionuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import tn.esprit.gestionuser.model.User
import tn.esprit.gestionuser.respository.UserRep
import tn.esprit.gestionuser.respository.UserRepositoryImpl
import kotlinx.coroutines.launch
import java.lang.Exception


class UserViewModel  (private  val userRepository: UserRep): ViewModel() {
    /*private val _userData = MutableLiveData<User>()
    val userData: LiveData<User> get() = _userData

    fun getUserData(){
        viewModelScope.launch {
            try{
                val user = userRepository.getUserData()
                _userData.value = user
            }catch (e:Exception){
                
            }
        }
    }*/
}