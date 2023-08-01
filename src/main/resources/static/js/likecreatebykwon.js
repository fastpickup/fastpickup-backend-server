
// Like Axios & JavaScript Code 

const urlParams = new URLSearchParams(window.location.search);
const pno = window.location.pathname.split('/').pop();

// querySelector 등록 likeCount actionLike 
const likeCountElement = document.querySelector(".likeCount");
const likeButton = document.querySelector(".actionLike");

// Like Path 
const likeLink = "http://localhost:8080/like"

const updateLikeCount = async (pno) => {
    const newCount = await toggleGet(pno);
    likeCountElement.innerText = newCount;
};

document.addEventListener("DOMContentLoaded", async () => {
    await updateLikeCount(pno);
});

likeButton.addEventListener("click", async () => {
    await toggleLike(pno);
    await updateLikeCount(pno);
    // Action Color Change
    toggleLikeButtonColor();
});

// Get Like Read Axios
const toggleGet = async (pno) => {
    const response = await axios.get(`${likeLink}/pno/${pno}/count`);
    return response.data.result;
};

// Count Like Axios
const toggleLike = async (pno) => {
    const response = await axios.post(`${likeLink}/pno/toggle/${pno}`);
    updateLikeCount(pno);
    return response.data;
};

// Action Color Change
const toggleLikeButtonColor = () => {
    likeButton.classList.toggle("liked");
};