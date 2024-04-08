import React from 'react';
import Alert from '@mui/material/Alert';
import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

import './updateError.scss'; // Import the SCSS file

const ProfileErrorComponent = () => {


  return (
    <>
    <div>
    <Stack sx={{ width: '100%' }} spacing={20}>
        <Alert variant="filled" severity="warning" onClose={() => {}}>
          Please Update Your Profile First!!
        </Alert>
      </Stack>
    <center>
      <Button variant="contained" color="success" style={{ marginBottom: '50px' }}>
        Update Profile
      </Button>
    </center>
    </div>
      
      
      
    </>

    

    
    
  );
}

export default ProfileErrorComponent;
