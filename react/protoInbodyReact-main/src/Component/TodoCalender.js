import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import config from "../config";

export default function TodoCalender() {
  const navigate = useNavigate();
  const [userData, setUserData] = useState([]);

  // sessionStorage에서 JWT 토큰를 가져옵니다.
  const token = sessionStorage.getItem("token");
  const userid = sessionStorage.getItem("userid");

  useEffect(() => {
    if (token) {
      fetch(`http://${config.SERVER_URL}/request/diet-records/${userid}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("서버 응답이 실패했습니다.");
          }
          return response.json();
        })
        .then((data) => {
          console.log("받은 데이터:", data);
          setUserData(data);
        })
        .catch((error) => console.error("데이터 불러오기 실패:", error));
    }
  }, [token]);

  const navigateToFood = () => {
    navigate("/food");
  };

  return (
    <div>
      <h2>내 식단 기록</h2>

      {userData && userData.length > 0 ? (
        userData.map((record, index) => (
          <div key={index}>
            <p> 메모: {record.dietMemo || "메모 없음"}</p>
            <p>
              날짜:{" "}
              {record.timestamp
                ? new Date(record.timestamp).toLocaleDateString("ko-KR")
                : "날짜 없음"}
            </p>
            <p> 음식: {record.foodNm || "음식 없음"}</p>
            <p> 칼로리: {record.enerc || 0} kcal</p>
            <p> 단백질: {record.prot || 0}g</p>
            <p> 탄수화물: {record.chocdf || 0}g</p>
            <p> 지방: {record.fatce || 0}g</p>
            <p> 제조사: {record.mfrNm || 0}g</p>

            <hr />
          </div>
        ))
      ) : (
        <p>기록이 없습니다.</p>
      )}

      <button onClick={navigateToFood}>음식 검색</button>
    </div>
  );
}
