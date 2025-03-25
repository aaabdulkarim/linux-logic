<template>
  <div class="profile-container grid">
    <div class="profile-header">
      <!-- Profilbild und Benutzerinfo -->
      <div class="profile-left">
        <img src="@/assets/LinuxLogic_Maskottchen.png" alt="Maskottchen" class="profile-image" />
        <div class="profile-info">
          <p class="profile-title">Profil</p>
          <h1 class="profile-name">{{ profileName }}TestName</h1>
          <h6 class="profile-email">{{ email }} TestEmail</h6>
          <p class="profile-progress">Fortschritt: <span class="level">{{ progressLevel }}TestLvl</span></p>
          <a class="change-password">Passwort ändern</a>
          <button class="log-out" @click="logOut">Abmelden</button>
        </div>
      </div>
    <div class="next-course">
      <h2 style="margin-top: 0px;">Level Name</h2>
      <button class="next-level" @click="playNextLevel">Nächstes Level</button>
    </div>
    </div>
    <div class="course-progress">
      <div class="header-row">
        <div class="completed-levels">
          <h2>Abgeschlossene Level</h2>
          <div class="course-list" v-for="(chapter, index) in chapters" :key="index">
            <h3 @click="toggleChapter(index)" class="chapter-header">{{ chapter.name }}</h3>
            <div v-if="chapter.expanded" class="course-cards">
              <div class="course-card" v-for="(course, idx) in chapter.courses" :key="idx">
                <div class="course-content">{{ course.name }}</div>
                <div class="course-stars">
                  <span v-for="star in course.stars" :key="star" class="star">⭐</span>
                </div>
              </div>
            </div>
          </div>
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
import axios from 'axios';
import api from '@/api';

export default {
  data() {
    return {
      profileName: '',
      email: '',
      progressLevel: '',
      chapters: [
        {
          name: "Kapitel 1",
          expanded: false,
          courses: [
            { name: "Level 1", stars: 0 },
            { name: "Level 2", stars: 0 },
            { name: "Level 3", stars: 0 },
            { name: "Level 4", stars: 0 },
            { name: "Level 5", stars: 0 }
          ]
        },
        {
          name: "Kapitel 2",
          expanded: false,
          courses: [
            { name: "Level 6", stars: 0 },
            { name: "Level 7", stars: 0 },
            { name: "Level 8", stars: 0 },
            { name: "Level 9", stars: 0 },
            { name: "Level 10", stars: 0 }
          ]
        }
      ],
      nextCourse: { name: "Level 11", stars: 0 },
      badges: []
    };
  },
  methods: {
    navigateToMenu() {
      this.$router.push('/auswahl');
    },
    playNextLevel() {
      this.$router.push('/terminal');
    },
    toggleChapter(index) {
      this.chapters[index].expanded = !this.chapters[index].expanded;
    },
    fetchUserData() {
      api.get('/api/user/profile')
        .then(response => {
          const data = response.data;
          this.profileName = data.name;
          this.email = data.email;
          this.progressLevel = data.progressLevel;
          this.chapters = data.chapters.map(chapter => ({
            ...chapter,
            expanded: true,
            courses: chapter.courses.map(course => ({
              ...course,
              stars: course.stars || 0
            }))
          }));
          this.nextCourse = data.nextCourse;
          this.badges = data.badges;
        })
        .catch(error => {
          console.error('Fehler beim Abrufen der Benutzerdaten:', error);
        });
    }
  },
  mounted() {
    this.fetchUserData();
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
  color: #3D525C;
}
.profile-header {
  display: flex;
  width: 100%;
  max-width: 800px;
  justify-content: space-between;
}
.profile-left {
  display: flex;
  align-items: left;

}
.profile-image {
  width: 250px;
  height: 250px;
  background: #d3d3d3;
  border-radius: 10px;
  margin-right: 24px;
}
.profile-info {
  display: flex;
  flex-direction: column;
  text-align: left;
}
.profile-title {
  font-size: 16px;
  color: #3D525C;
  margin-bottom: 0px;
}
.profile-name {
  margin-top: 2px;
  color: #3D525C;
  font-size: 32px;
}
.profile-email {
  margin-top: 2px;
  color: #3D525C;
  font-size: 12px;
}
.profile-progress .level {
  color: #569191;
}
.change-password {
  font-size: 16px;
  background: none;
  border: none;
  color: #569191;
  cursor: pointer;
  display: inline-block;
}
.course-progress {
  display: flex;
  width: 100%;
  max-width: 800px;
  margin-top: 30px;
}
.header-row {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}
.completed-levels {
  flex: 1;
}
.course-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.chapter-header {
  cursor: pointer;
  background: #569191;
  color: white;
  padding: 10px;
  border-radius: 5px;
  text-align: center;
}
.chapter-header:hover {
  background: #3D525C;
}
.course-cards {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
}
.course-card {
  width: 100%;
  background: #7a9a9a;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: white;
  font-size: 18px;
  font-weight: bold;
  padding: 10px;
}
.course-card:hover {
  background: #3D525C;
}
.next-level {
    color: white;
    background-color: #569191;
    border: 2px solid #569191;
    border-radius: 10px;
    transition: background-color 0.3s ease;
    width: 200px;
    height: 46px;
}
  
.next-level:hover {
    color: #569191;
    background-color: white;
    border: 2px solid #569191;
}
.log-out {
  font-size: 16px;
  width: 123px;
  background: none;
  border: 2px solid darkred;
  color: darkred;
  cursor: pointer;
  display: inline-block;
  padding: 3px;
  border-radius: 5px;
  margin-top: 10px;
}
.log-out:hover {
  background: darkred;
  color: white;
}

@media (max-width: 740px) {
  .profile-header {
    flex-direction: column;
    align-items: flex-start;
    text-align: left;
  }

  .profile-left {
    flex-direction: row;
    align-items: flex-start;
  }

  .profile-image {
    margin-bottom: 0;
  }

  .profile-info {
    text-align: left;
  }

  .next-course {
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    align-items: center; /* Zentriert Button & Überschrift */
    justify-content: center;
  }
}

@media (max-width: 480px) {
  .profile-header {
    flex-direction: column;
    align-items: center;
    text-align: center;
  }

  .profile-left {
    flex-direction: column;
    align-items: center;
    width: 100%;
  }

  .profile-image {
    margin-bottom: 10px;
  }

  .course-cards {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .profile-info {
    text-align: center;
  }

  .next-course {
    display: flex;
    flex-direction: column;
    align-items: center; /* Zentriert Button & Überschrift */
    justify-content: center;
  }

  .course-card {
    width: 48%;
  }
}
</style>
