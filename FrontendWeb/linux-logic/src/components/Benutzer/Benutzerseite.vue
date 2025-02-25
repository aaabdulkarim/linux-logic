<template>
  <div class="profile-container">
    <div class="profile-header">
      <div class="profile-info">
        <h1 class="profile-name">Profilname</h1>
        <p class="profile-progress">Fortschritt: <span class="level">Level 5</span></p>
      </div>
      <div class="profile-actions">
        <p class="stay-logged-in">Email</p>
        <button class="change-password">Passwort ändern</button>
      </div>
    </div>

    <div class="course-progress">
      <div class="completed-levels">
        <h2>Abgeschlossene Level</h2>
        <div class="course-list" v-for="(chapter, index) in chapters" :key="index">
          <h3 @click="toggleChapter(index)" class="chapter-header">{{ chapter.name }}</h3>
          <div v-if="chapter.expanded" class="course-cards">
            <div class="course-card" v-for="(course, index) in chapter.courses" :key="index">
              <div class="course-content">{{ course.name }}</div>
              <div class="course-stars">
                <span v-for="star in course.stars" :key="star" class="star">⭐</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="next-course">
        <h2>Nächstes Level</h2>
        <div class="next-course-card">
          <div class="course-content">{{ nextCourse.name }}</div>
          <div class="course-stars">
            <span v-for="star in nextCourse.stars" :key="star" class="star">⭐</span>
          </div>
          <button class="next-level" @click="playNextLevel">Nächstes Level spielen</button>
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
      chapters: [
        {
          name: "Kapitel 1",
          expanded: false,
          courses: [
            { name: "Level 1", stars: 3 },
            { name: "Level 2", stars: 2 },
            { name: "Level 3", stars: 4 },
            { name: "Level 4", stars: 1 },
            { name: "Level 5", stars: 3 }
          ]
        },
        {
          name: "Kapitel 2",
          expanded: false,
          courses: [
            { name: "Level 6", stars: 3 },
            { name: "Level 7", stars: 2 },
            { name: "Level 8", stars: 4 },
            { name: "Level 9", stars: 1 },
            { name: "Level 10", stars: 3 }
          ]
        }
      ],
      nextCourse: { name: "Level 11", stars: 0 },
      badges: ["Label", "Label", "Label", "Label", "Label", "Label"]
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
    }
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
.navigation-buttons {
  display: flex;
  gap: 10px;
  margin-top: 20px;
}
.back-to-menu, .next-level {
  background: #569191;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
.course-progress {
  display: flex;
  width: 100%;
  max-width: 800px;
  margin-top: 30px;
}
.course-list {
  display: flex;
  flex-direction: column;
  gap: 10px;
  flex: 1;
}
.chapter-header {
  cursor: pointer;
  background: #569191;
  color: white;
  padding: 10px;
  border-radius: 5px;
  text-align: center;
  margin: 5px;
}
.course-cards {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 10px;
  margin: 5px;
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
.course-stars {
  margin-top: 10px;
}
.star {
  font-size: 20px;
  color: gold;
}
.next-course {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-left: 20px;
}
.next-course-card {
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
  padding: 20px;
}
.achievements {
  width: 100%;
  max-width: 800px;
  margin-top: 30px;
}
.achievement-list {
  display: flex;
  gap: 10px;
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
