<template>
    <div class="profile-container">
      <div class="profile-header">
        <div class="profile-image"></div>
        <div class="profile-info">
          <h1 class="profile-name">Profilname</h1>
          <p class="profile-progress">Fortschritt: <span class="level">Level 5</span></p>
        </div>
        <div class="profile-actions">
          <p class="stay-logged-in">Email</p>
          <button class="change-password">Passwort ändern</button>
        </div>
      </div>
      
      <div class="current-course">
        <h2>Weiterlernen</h2>
        <div class="course-list">
          <div class="course-card" v-for="(course, index) in courses" :key="index">
            <div class="course-content">{{ course.name }}</div>
          </div>
        </div>
      </div>
      
      <div class="achievements">
        <h2>Erfolge</h2>
        <div class="achievement-list">
          <div class="achievement" v-for="(badge, index) in badges" :key="index">
            <div class="badge-icon"></div>
            <p class="badge-label">{{ badge }}</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        courses: [
          { name: "1st" },
          { name: "2nd" },
          { name: "3rd" },
          { name: "4th" }
        ],
        badges: ["Label", "Label", "Label", "Label", "Label", "Label"]
      };
    }
  };
  </script>
  
  <style scoped>
  .profile-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background: white;
    padding: 40px 20px;
    color: #333;
  }
  .profile-header {
    display: flex;
    width: 100%;
    max-width: 800px;
    justify-content: space-between;
    align-items: center;
  }
  .profile-image {
    width: 100px;
    height: 100px;
    background: #d3d3d3;
    border-radius: 10px;
  }
  .profile-info {
    flex-grow: 1;
    margin-left: 20px;
  }
  .profile-name {
    font-size: 24px;
    font-weight: bold;
  }
  .profile-progress .level {
    color: #569191;
  }
  .profile-actions {
    text-align: right;
  }
  .change-password {
    background: none;
    border: none;
    color: #569191;
    cursor: pointer;
  }
  .current-course, .achievements {
    width: 100%;
    max-width: 800px;
    margin-top: 30px;
  }
  .course-list, .achievement-list {
    display: flex;
    gap: 10px;
  }
  .course-card {
    flex: 1;
    min-width: 150px;
    height: 150px;
    background: #7a9a9a;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    color: white;
    font-size: 18px;
    font-weight: bold;
  }
  .achievement-list {
    justify-content: center;
  }
  .achievement {
    text-align: center;
  }
  .badge-icon {
    width: 50px;
    height: 50px;
    background: #d3d3d3;
    border-radius: 50%;
    margin-bottom: 5px;
  }
  .badge-label {
    font-size: 14px;
    font-weight: bold;
  }
  </style>
  