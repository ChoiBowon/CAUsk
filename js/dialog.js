function $(id) {
  return document.querySelector(id);
}

function showChart(isVisible) {
     if(isVisible) $("#myModal").classList.add("visible");
     else $("#myModal").classList.remove("visible");

    //  if(isVisible) $("#menuModal").classList.add("visible");
    //  else $("#menuModal").classList.remove("visible");
}
function showRevision(isVisible){
    if(isVisible) $("#menuModal").classList.add("visible");
    else $("#menuModal").classList.remove("visible");
}

function addMenuListener(evt) {
  evt.preventDefault();
    showChart(true);
}

function closeMenuListener(evt) {
  evt.preventDefault();
    showChart(false);
}

function revisionMenuListener(evt){
  evt.preventDefault();
  fillContentOfDialog(evt.target.closest(".menu").firstElementChild.innerHTML, evt.target.closest("tr").children);
  console.log(evt.target.getAttribute("data-id"));
  var data = evt.target.getAttribute("data-id");
  console.log(data);
  document.getElementById("divdata").innerHTML=data;
  showRevision(true);
}

function fillContentOfDialog(menuName, contents) {
    console.log(menuName)
    console.log(contents[0].firstElementChild);
    console.log(contents[1]);
    console.log(contents[2]);
    console.log(contents[3]);

    $("#menuModal").firstElementChild.children[1].firstElementChild.children[0].lastElementChild.value = menuName
    $("#menuModal").firstElementChild.children[1].firstElementChild.children[1].lastElementChild.value = contents[0].firstElementChild.innerText
    $("#menuModal").firstElementChild.children[1].firstElementChild.children[2].lastElementChild.value = contents[1].innerText
    $("#menuModal").firstElementChild.children[1].firstElementChild.children[3].lastElementChild.value = contents[2].innerText
    $("#menuModal").firstElementChild.children[1].firstElementChild.children[4].lastElementChild.value = contents[3].innerText
}

function closeMenuListener2(evt) {
  evt.preventDefault();
  console.log("close1")
    showRevision(false);
}


function categoryDirect(){
  if(document.join.category_selbox.value == 'direct'){
    document.join.CategoryDirect.disabled = false;
    document.join.CategoryDirect.value = "";
    document.join.CategoryDirect.focus();
  }else{
    document.join.CategoryDirect.disabled = true;
    document.join.CategoryDirect.value = document.join.category_selbox.options[document.join.category_selbox.selectedIndex].value;
    // document.getElementById('categoryDirect').value = document.join.category_selbox.options[document.join.category_selbox.selectedIndex].value;

  }
}

function sizeDirect(){
  if(document.join.size_selbox.value == 'direct'){
    document.join.SizeDirect.disabled = false;
    document.join.SizeDirect.value = "";
    document.join.SizeDirect.focus();
  }else{
    document.join.SizeDirect.disabled = true;
    document.join.SizeDirect.value = document.join.size_selbox.options[document.join.size_selbox.selectedIndex].value;
  }

}

function hot(){
  document.join.hotDirect.disabled = true;
  //console.log(document.join.hot_selbox.options[document.join.hot_selbox.selectedIndex].value);
  document.join.hotDirect.value = document.join.hot_selbox.options[document.join.hot_selbox.selectedIndex].value;
}


document.addEventListener("DOMContentLoaded", function() {
  showChart(false);
  showRevision(false);
  $("#myBtn").addEventListener("click", addMenuListener);
  $("#close").addEventListener("click", closeMenuListener);
  // $("#submit").addEventListener("click", closeMenuListener);
  // $(".menuBtn").addEventListener("click", revisionMenuListener);
  document.querySelectorAll(".menuBtn").forEach((btn) => {
      btn.addEventListener("click", revisionMenuListener);
    })
  $("#close2").addEventListener("click", closeMenuListener2);



})
