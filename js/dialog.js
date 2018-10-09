function $(id) {
  return document.querySelector(id);
}

function showChart(isVisible) {
     if(isVisible) $("#myModal").classList.add("visible");
     else $("#myModal").classList.remove("visible");
}

function addMenuListener(evt) {
  evt.preventDefault();
    showChart(true);
}

document.addEventListener("DOMContentLoaded", function() {
  showChart(false);
  $("#myBtn").addEventListener("click", addMenuListener);
})
