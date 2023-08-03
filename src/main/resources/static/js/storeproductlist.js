//스토어 상품 리스트
const getList = async(page = 1) => {
  const res = await axios.get(`http://localhost:8080/admin/product/${listStore.sno}/list?page=${page}`)
  return res.data
}

const productListDefault = (page) => {
  getList(page).then(arr => {

  })
}