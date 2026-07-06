<template>
  <section class="support-form">
    <h1>Reach out for help</h1>
    <form @submit.prevent="submitForm">
      <div class="form-group">
        <label for="subject">Subject</label>
        <input type="text" id="subject" v-model="form.subject" required>
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <textarea id="description" v-model="form.description" rows="5" required></textarea>
      </div>

      <button type="submit" class="submit-button">Submit</button>
      <p v-if="message" class="message messageFadeIn">{{ this.message }}</p>
    </form>
  </section>
</template>

<script>
export default {
  name: 'SupportForm',
  data() {
    return {
      form: {
        subject: '',
        description: '',
        message: null
      }
    };
  },
  methods: {
    submitForm() {
      this.message = 'Support request submitted!';
      this.form.subject = '';
      this.form.description = '';
      this.form.email = '';
    }
  }
};
</script>

<style scoped>
.support-form {
  max-width: 500px;
  margin: 2rem auto;
  padding: 1.5rem;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h1 {
  font-size: 1.8rem;
  color: #333;
  margin-bottom: 1rem;
}

.form-group {
  margin-bottom: 1rem;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
  color: #333;
}

input[type="text"],
input[type="email"],
textarea {
  width: 100%;
  padding: 0.8rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  font-size: 1rem;
  resize: vertical;
  max-width: 100%;
}

.submit-button {
  margin-top: 1rem;
  padding: 0.6rem 1.2rem;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #10ec56;
}
</style>
