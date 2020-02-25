package com.example.dmii.fragments

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.dmii.R

class LocationFragment : Fragment() {

    companion object {
        const val PERMISSION_CODE = 8
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.location_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         val activity = activity ?: return

        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                val builder: AlertDialog.Builder? = activity.let {
                    AlertDialog.Builder(it)
                }

                builder?.setMessage("oui")
                    ?.setTitle("non")
                builder?.create()
            } else {

                needConfirmation(activity)

               /* val alertDialog: AlertDialog? = activity.let {
                    val builderoui = AlertDialog.Builder(it)
                    builder?.apply {
                        setPositiveButton(R.string.ok,
                            DialogInterface.OnClickListener { dialog, id ->
                                needConfirmation(activity)
                            })
                        setNegativeButton(R.string.cancel,
                            DialogInterface.OnClickListener { dialog, id ->
                                // User cancelled the dialog
                            })
                    }
                    builder?.create()
                }*/
            }
        } else {
            val fragment = ChoiceFragment()

            activity.supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, fragment)
                addToBackStack(null)
            }.commit()
        }
    }

    fun needConfirmation(activity: FragmentActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //La permission est accordée, on adapte la vue en conséquence
                } else {
                    //Permission refusée, on laisse l'utilisateur tranquille
                    //On peut désactiver certaines fonctionnalités de l'app
                    //On affiche un message d'erreur
                }
            }
            else -> {
                // Le code ne concerne pas la permission, on l'ignore
            }
        }
    }


}