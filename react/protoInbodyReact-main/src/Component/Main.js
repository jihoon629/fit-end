import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function Main() {
  const userid = sessionStorage.getItem("userid");
  const navigate = useNavigate();
  const [bodyrecod, setbodyrecod] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigateToRecordBody = () => {
    navigate("/recodbody");
  };

  useEffect(() => {
    fetch(`http://localhost:8080/download/recentuserbody/${userid}`)
      .then((response) => response.json())
      .then((data) => {
        setbodyrecod(data);
        setLoading(false); // 데이터 로드 완료 후 로딩 상태 업데이트
      })
      .catch((error) => {
        console.error("Error fetching users:", error);
        setLoading(false); // 에러 발생 시에도 로딩 상태 업데이트
      });
  }, [userid]);

  console.log(bodyrecod, "여기여");

  if (bodyrecod[0] == null) {
    return (
      <div>
        오류임
        <button onClick={navigateToRecordBody}>Go to RecordBody</button>
      </div>
    );
  }

  return (
    <div>
      {userid ? (
        <>
          <h2>Main Screen</h2>
          <p>Welcome to the main screen!</p>
          <p>Logged in as: {userid}</p>

          <div>
            <h2>📊 InBody 결과</h2>
            {loading ? (
              <p>데이터를 불러오는 중입니다...</p> // 로딩 중 메시지
            ) : (
              <>
                <p>
                  <strong>📏 키:</strong> {bodyrecod[0].height} cm
                </p>
                <p>
                  <strong>⚖️ 몸무게:</strong> {bodyrecod[0].weight} kg
                </p>
                <p>
                  <strong>📉 체지방률 :</strong> {bodyrecod[0].fatpercentage} %
                </p>
                <p>
                  <strong>💪 BMI:</strong> {bodyrecod[0].bmi}
                </p>
                <p>
                  <strong>🔥 InBody Score:</strong> {bodyrecod[0].inbodyScore}
                </p>
              </>
            )}
          </div>

          <button onClick={navigateToRecordBody}>Go to RecordBody</button>
        </>
      ) : (
        <p>잘못된 접근</p>
      )}
    </div>
  );
}
