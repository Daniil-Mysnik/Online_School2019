function setCoockies() {
  const coockieName = document.getElementById('coockieName').value
  const coockieValue = document.getElementById('coockieValue').value
  console.log(coockieName + '=' +coockieValue);
  document.cookie = coockieName + "=" +coockieValue;
};


