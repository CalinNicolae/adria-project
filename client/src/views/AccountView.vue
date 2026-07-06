<template>
  <section class="account-view">
    <h1>Account</h1>
    <div class="profile-container">
      <img :src="user.profileImage" alt="Profile Image" class="profile-image">
      <div class="profile-details">
        <div class="detail-box">
          <label><strong>CropTek ID</strong></label>
          <p>{{ user.cropTekId }}</p>
        </div>
        <div class="detail-box">
          <label><strong>Company Name</strong></label>
          <p>{{ user.companyName }}</p>
        </div>
        <div class="detail-box">
          <label><strong>Address</strong></label>
          <p>{{ user.address }}</p>
        </div>
        <div class="detail-box">
          <label><strong>Adria mail</strong></label>
          <p>{{ user.email }}</p>
        </div>
        <div class="detail-box">
          <label><strong>Adria number</strong></label>
          <p>{{ user.phoneNumber }}</p>
        </div>
      </div>
    </div>

    <div class="button-container">
      <button class="support-form-button">
        <RouterLink to="/support">Get Help!</RouterLink>
      </button>
    </div>
  </section>

</template>

<script>
export default {
  name: 'AccountView',
  data() {
    return {
      user: {
        cropTekId: '123456',
        companyName: 'Megatron Oblivionstrike',
        address: 'Oblivion Crater, Nr. 13, 420-123',
        profileImage: new URL('@/assets/images/defaultprofileimage.jpg', import.meta.url).href, // Dynamic image path
        email: 'ceoofadriafarm@adriamail.adria.co',
        phoneNumber: '200 300 599 999',
        // Add other user details here
      }
    };
  },
};
</script>
<style scoped>
button {
  margin: 0.5rem;
}

.account-view {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.profile-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  background-color: #f9f9f9;
  padding: 1rem;
  border-radius: 0.5rem;
  box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s;
}

.profile-container:hover {
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.2);
}

.profile-image {
  width: 9.375rem;
  height: 9.375rem;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 0.2rem 0.5rem rgba(0, 0, 0, 0.1);
  border: 0.2rem solid #2ecc71;
}

.profile-details {
  width: 100%;
  max-width: 20rem;
}

.detail-box {
  background-color: #ecf0f1;
  padding: 0.5rem;
  border-radius: 0.3rem;
  margin: 0.5rem 0;
  transition: transform 0.3s;
}

.detail-box:hover {
  transform: scale(1.05);
}

.detail-box label {
  display: block;
  color: #ffffff;
  background-color: #2ecc71;
  padding: 0.2rem 0.5rem;
  border-radius: 0.3rem;
  margin-bottom: 0.2rem;
  font-weight: bold;
}

.detail-box p {
  margin: 0;
  font-size: 1.1rem;
  color: #2c3e50;
}

.drone-action-button,
.edit-profile-button,
.support-form-button {
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
}
</style>
