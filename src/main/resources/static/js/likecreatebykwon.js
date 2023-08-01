// Like Axios & JavaScript Code 

const urlParams = new URLSearchParams(window.location.search);
const pno = window.location.pathname.split('/').pop();

// querySelector 등록 likeCount actionLike 
const likeCountElement = document.querySelector(".likeCount");
const likeButton = document.querySelector(".actionLike");

// Like Path 
const likeLink = "http://localhost:8080/like"

// Get Like Count
const updateLikeCount = async (pno) => {
    const response = await axios.get(`${likeLink}/pno/${pno}/count`);
    likeCountElement.innerText = response.data.result;
};

// Count Like
const toggleLike = async (pno) => {
    const response = await axios.post(`${likeLink}/pno/toggle/${pno}`);
    await updateLikeCount(pno);
    toggleLikeButtonColor();
};

// Action Color Change
const toggleLikeButtonColor = () => {
    likeButton.classList.toggle("liked");
};

// Check Like
const checkLike = async (pno) => {
    const response = await axios.get(`${likeLink}/pno/${pno}/check`);
    if (response.data.liked) {
        likeButton.classList.add("liked");
    } else {
        likeButton.classList.remove("liked");
    }
};

// Event Listeners
document.addEventListener("DOMContentLoaded", async () => {
    await checkLike(pno);
    await updateLikeCount(pno);
});

likeButton.addEventListener("click", async () => {
    await toggleLike(pno);
});
