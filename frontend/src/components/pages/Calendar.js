import React, { Component } from 'react'  
import FullCalendar from "@fullcalendar/react";  
import dayGridPlugin from "@fullcalendar/daygrid";  
import timeGridPlugin from "@fullcalendar/timegrid";  
  
function DemoApp() {  
    const data = JSON.parse(localStorage.getItem("reservation"));
    console.log(data);
    const events = [{ title: "Today", date: new Date() }];  
        return (   
                 <FullCalendar  
              defaultView="dayGridMonth"  
             header={{  
            left: "prev,next",  
            center: "title",  
           right: "dayGridMonth,timeGridWeek,timeGridDay"  
        }}  
        plugins={[dayGridPlugin, timeGridPlugin]}  
        events={events}  
      />  
        )
  
}  
  
export default DemoApp  