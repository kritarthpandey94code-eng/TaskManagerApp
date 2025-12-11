# Task Manager App

A simple 2-screen Android app that allows users to view a list of tasks, update their status, and filter them. Built using **Kotlin**, **MVVM**, **RecyclerView**, and **SharedPreferences**.  

---

## Features

1. **Task List Screen (MainActivity)**  
   - Displays a list of tasks in a RecyclerView.  
   - Each task shows a **title**, **description**, and **status** (Pending/Completed).  
   - Filter button at the bottom allows viewing **All**, **Pending**, or **Completed** tasks.  

2. **Detail Screen (SecondScreenActivity)**  
   - Displays task details (title, description, current status).  
   - Button allows toggling status between **Pending** and **Completed**.  
   - Button text dynamically updates based on the current status.  

3. **Persistent Storage**  
   - Task status is saved in **SharedPreferences**, so changes remain after app restart.  

---

## Approach

1. **MVVM Pattern**  
   - `FirstScreenViewModel` manages the list of tasks.  
   - MainActivity observes changes and updates the RecyclerView using `SampleAdapter`.  

2. **RecyclerView Adapter**  
   - `SampleAdapter` handles displaying task data.  
   - Colors status text green for completed, red for pending.  
   - Reads the latest status from SharedPreferences to reflect updates.  

3. **SharedPreferences**  
   - `SharedPrefHelper` class abstracts reading/writing task status.  
   - Status changes are persisted immediately when toggled in the detail screen.  

4. **Filtering**  
   - Bottom button in MainActivity opens a dialog with options: All, Pending, Completed.  
   - RecyclerView is filtered based on the selected option using the saved statuses.  

5. **Navigation**  
   - Clicking a task in MainActivity opens SecondScreenActivity with the task details.  
   - Status changes are reflected when returning to MainActivity.  

---

## Why this approach?

- **MVVM** separates UI and data logic, making the app easier to maintain and scale.  
- **SharedPreferences** is lightweight and perfect for small persistent data like task status.  
- **RecyclerView with adapter** allows dynamic updates and filtering efficiently.  
- **Single-activity navigation** with Intent extras is simple for a 2-screen app.  

---

## Possible Improvements (if I had one more day)

- **Add Room Database** for more robust data storage, instead of SharedPreferences.  
- **Live data for status updates** so the main screen updates automatically without needing to reload.  
- **Animations** when changing status or filtering tasks to improve UX.  
- **Search functionality** to quickly find tasks by title or description.  
- **Material Design improvements**, e.g., swipe to mark completed/pending.  

---

## How to Run

1. Clone the repository:
   ```bash
   git clone <repository_url>

2. Run the project
