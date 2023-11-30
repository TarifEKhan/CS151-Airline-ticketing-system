import { useState } from 'react';
import { Form } from './Form';
import { flightData } from '../constants/FlightData';

const findFlight = (fromCity, toCity) => {
   const matchingFlights = flightData.filter((flight) => {
      return (
         flight.fromCity === fromCity &&
         flight.toCity === toCity
      );
   });
   return matchingFlights;
};


export const Modal = ({ fromCity, toCity, onClose }) => {

   const [flights, setFlights] = useState(findFlight(fromCity, toCity));
   const [selectedFlight, setSelectedFlight] = useState(null);
   const [isSwapped, setIsSwapped] = useState(false);
   const [successMessage, setSuccessMessage] = useState('');

   const handleSwapCities = () => {
      const newFromCity = isSwapped ? fromCity : toCity;
      const newToCity = isSwapped ? toCity : fromCity;
      setIsSwapped(!isSwapped);
      setFlights(findFlight(newFromCity, newToCity));
   };

   const handlePurchase = (formData) => {
      // Decrease the number of available seats
      const updatedFlights = flights.map((flight) => {
         if (flight.id === selectedFlight.id && 
            flight.seatAvailability > 0) {
            return {
               ...flight,
               seatAvailability: flight.seatAvailability - 1,
            };
         }
         return flight;
      });
      setFlights(updatedFlights);

      // Show success message
      setSuccessMessage('Ticket purchased successfully!');
   };

   
   return (
      <div className="modal">
         {/* The Modal Heading */}
         <div className="modal_bigText">Available Flights</div>
         <button onClick={handleSwapCities}>Swap Cities</button>

         {/* The Ticket Purchase Form
         Show up only selected a ticket */}
         {selectedFlight && (
            <div>
               <Form 
                  selectedFlight={selectedFlight} 
                  onPurchase={handlePurchase} 
               />
            </div>
         )}


         {/* The Ticket Information */}
         <div className="flight_tickets">
            {flights.map((flight) => (
               <div className="ticket" 
                  key={flight.id} 
                  onClick={() => setSelectedFlight(flight)}
               >
                  <div>
                     <strong>From:</strong> {flight.fromCity}
                     <span className="space_css"></span>
                     <strong>To:</strong> {flight.toCity}
                  </div>
                  <div>
                     <strong>Flight Number: </strong> 
                     {flight.flightNumber} 
                  </div>
                  <div>
                     <strong>Time: </strong>
                     {flight.departureTime} - {flight.arrivalTime}
                  </div>
                  <div>
                     <strong>Seats Available: </strong> 
                     {flight.seatAvailability}
                  </div>
                  <div>
                     <strong>Price: </strong>
                     ${flight.price}
                  </div>
               </div>
            ))}
         </div>
         <button onClick={onClose}>Close</button>

         {successMessage && 
            <div className="success_msg">
               {successMessage}
            </div>
         }
      </div>
   )
}
