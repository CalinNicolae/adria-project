self.addEventListener("push", e => {
    const data = e.data.json();
    self.registration.showNotification("Hello", {
        body: data.message,
        data: {
            url: data.url
        }
    });
});