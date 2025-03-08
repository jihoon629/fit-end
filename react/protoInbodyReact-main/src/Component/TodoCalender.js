import React from "react";
import { useNavigate } from "react-router-dom";

export default function TodoCalender() {
  const navigate = useNavigate();

  const navigateToFood = () => {
    navigate("/food");
  };
  return (
    <div>
      <div>
        제 생각으로는 pros 써서 날짜랑 아침, 점심, 저녁 값을 foodsearch에
        넘겨주고
      </div>
      <div>전부다 json에 떄려 박으면 될듯</div>
      <div>그리고 다시 유저 데이터 받아서 정보 여기에 띄우고</div>

      <button onClick={navigateToFood}>음식 검색</button>
    </div>
  );
}
