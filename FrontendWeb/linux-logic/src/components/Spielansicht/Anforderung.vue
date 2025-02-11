<template>
<div class="all grid">
  <div class="container">
    <div class="header ">
      <h1>Level 1</h1>
    </div>
    <div class="content">
      <h2>Beschreibung</h2>
      <p>Den roten Teppich ausrollen:

        Wechsel in das Verzeichnis der Veranstaltungsvorbereitung: cd
        /home/Veranstaltung .
        Erstelle eine Datei, die den roten Teppich darstellt: touch roter_teppich.txt .
      </p>
    </div>
    <div class="terminal-container">
      <Terminal/>
    </div>
    <div class="icon-container">
      <i class="pi pi-angle-left icon"></i>
      <div class="right-icons">
        <i class="pi pi-pencil icon" @click="showModal('edit')"></i>
        <i class="pi pi-key icon" @click="showModal('key')"></i>
        <i class="pi pi-angle-right icon"></i>
      </div>
    </div>
    
    <div v-if="isModalVisible" class="modal">
      <div class="modal-content">
        <span class="close" @click="closeModal">&times;</span>
        <p>{{ modalContent }}</p>
      </div>
    </div>
  </div>
</div>
</template>

<script>
import Terminal from './Terminal.vue';
export default {
  name: 'Anforderung',
  components: {
    Terminal
  },
  data() {
    return {
      isModalVisible: false,
      modalContent: ''
    };
  },
  methods: {
    showModal(type) {
      this.isModalVisible = true;
      this.modalContent = type === 'edit' ? 'Hier kannst du deine Aufgabe bearbeiten!' : 'Dies ist der Schlüsselhinweis!';
    },
    closeModal() {
      this.isModalVisible = false;
      this.modalContent = '';
    }
  }
}
</script>

<style scoped>
.all {
  background-color: white;
  padding-bottom: 18rem;
  justify-content: center;
}
.container {
  background-color: white;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: 1200px;
  padding-left: 10px;
  padding-right: 10px;
}
.content {
  justify-content: left;
  text-align: left;
  padding-left: 10px;
  padding-right: 10px;

}

.header {
  padding: 10px;
  color: white;
}

.terminal-container {
  width: 100%;
  max-width: 1200px;
  border-radius: 8px;
  margin-top: 20px;
}

.icon-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  margin-top: 20px;
  padding: 0 0;
}

.right-icons {
  display: flex;
  gap: 15px;
}

.icon {
  font-size: 24px;
  cursor: pointer;
}
</style>
