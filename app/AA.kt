<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widgets.ConstraintLayout
<androidx.constraintlayout.widget.ConstraintLayout
xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="#000000"

<!-- Top Bar -->
<androidx.constraintlayout.widget.ConstraintLayout
android:id="@+id/topBar"
android:layout_width="0dp"
android:layout_height="?attr/actionBarSize"
android:background="#000000"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent">


<ImageView
android:id="@+id/imageViewExample"
android:layout_width="match_parent"
android:layout_height="200dp"
android:scaleType="centerCrop"
android:src="@drawable/giornatanazionaledellospazio\"
app:layout_constraintTop_toBottomOf="@id/textViewExample"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintEnd_toEndOf="parent"/>

<TextView
android:id="@+id/tvInfinito"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="INFINITO.TO"
android:textColor="#FFFFFF"
android:textSize="18sp"
android:textStyle="bold"
app:layout_constraintStart_toStartOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
android:layout_marginStart="16dp" />

<ImageView
android:id="@+id/ivMenu"
android:layout_width="24dp"
android:layout_height="24dp"
android:src="@android:drawable/ic_menu_more"
android:tint="#FFFFFF"
app:layout_constraintEnd_toEndOf="parent"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
android:layout_marginEnd="16dp"
android:contentDescription="Menu" />

<ImageView
android:id="@+id/ivProfile"
android:layout_width="24dp"
android:layout_height="24dp"
android:src="@android:drawable/ic_menu_myplaces"
android:tint="#FFFFFF"
app:layout_constraintEnd_toStartOf="@+id/ivMenu"
app:layout_constraintTop_toTopOf="parent"
app:layout_constraintBottom_toBottomOf="parent"
android:layout_marginEnd="16dp"
android:contentDescription="Profilo" />

</androidx.constraintlayout.widget.ConstraintLayout>
