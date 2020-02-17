let cardContainer = document.querySelector("#notes-container");
let notes = [];
let noteIndex;

window.onload = () => {
  cards = getAllNotes();
}
document.querySelector(".display-note-modal-button").addEventListener("click", (e) => {
  e.preventDefault();
  displayCreateModal ();
})

document.querySelector(".create-note-button").addEventListener("click", (e) => {
  e.preventDefault();
  createNote();
})

document.querySelector(".update-note-button").addEventListener("click", (e) => {
  e.preventDefault();
  updateNote();
})


getAllNotes = async () => {
	try {
    let promise = await fetch("api/notes");
    notes = await promise.json();
    addNotesToDom();
	} catch (e) {
    console.log(e)
  }
}

deleteNote = async (e) => {
  let id = e.parentNode.parentNode.parentNode.dataset.id;
	try {
    await fetch(`api/notes/${id}`, { method: "DELETE" });
    e.parentNode.parentNode.parentNode.remove();
	} catch (e) {
    console.log(e)
  }
}

createNote = async () => {
  let title = document.querySelector(".note-title-input").value;
  let color = document.querySelector("input[type=radio]:checked").value;
  let json = JSON.stringify( {
    title,
    color
  });
  console.log(json);
  try {
    let promise = await fetch("api/notes", { 
      method: "POST",
      body: json,
      headers: {
        "Content-Type": "application/json"
      }
    });
    let note = await promise.json();
    addNoteToDom(note);
	} catch (e) {
    console.log(e)
  }
  removeCreateModal();
}

updateNoteModal = (index) => {
  displayUpdateModal();
  noteIndex = index;
  let note = notes[index];
  let title = document.querySelector(".note-title-update-input");
  title.value = note.title;
  if(document.querySelector(".radio-one-update").value === note.color) {
    document.querySelector(".radio-one-update").checked = true;
  }
  if(document.querySelector(".radio-two-update").value === note.color) {
    document.querySelector(".radio-two-update").checked = true;
  }
  if(document.querySelector(".radio-three-update").value === note.color) {
    document.querySelector(".radio-three-update").checked = true;
  }
  if(document.querySelector(".radio-four-update").value === note.color) {
    document.querySelector(".radio-four-update").checked = true;
  }
}

updateNote = async () => {
  let title = document.querySelector(".note-title-update-input").value;
  let color = document.querySelector("#update-note input[type=radio]:checked").value;
  let json = JSON.stringify( {
    title,
    color,
    tasks: notes[noteIndex].tasks,
    createDate: notes[noteIndex].createDate,
    lastUpdate: notes[noteIndex].lastUpdate
  });
  try {
    let promise = await fetch(`api/notes/${notes[noteIndex].id}`, { 
      method: "PUT",
      body: json,
      headers: {
        "Content-Type": "application/json"
      }
    });
    let note = await promise.json();
    console.log(note)
    notes[noteIndex] = note;
    addNotesToDom();
	} catch (e) {
    console.log(e)
  }
  removeUpdateModal();
}

addNotesToDom = () => {
  cardContainer.innerHTML = "";
	notes.forEach((note, index) => {
    addNoteToDom(note, index);
  })
}

addTasksToDom = (tasks) => {
  return "hello";
}

addNoteToDom = (note, index) => {
  let html = `<div class="note" style="background-color: ${note.color}" data-id="${note.id}">
                <div class="note-header">
                  <h2>${note.title}</h2>
                  <div>
                    <button onclick="updateNoteModal(${index})">edit</button>
                    <button onclick="deleteNote(this)" >X</button>
                  </div>
                </div>
                ${
                  addTasksToDom(note.tasks)
                }
              <div>`;
  cardContainer.insertAdjacentHTML("beforeend", html);
}

displayCreateModal = () => {
	document.querySelector("#add-note").style.display = "flex";
}

removeCreateModal = () => {
	document.querySelector("#add-note").style.display = "none";
}

displayUpdateModal = () => {
	document.querySelector("#update-note").style.display = "flex";
}

removeUpdateModal = () => {
	document.querySelector("#update-note").style.display = "none";
}