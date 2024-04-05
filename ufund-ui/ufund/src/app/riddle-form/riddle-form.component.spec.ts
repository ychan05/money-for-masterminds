import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RiddleFormComponent } from './riddle-form.component';

describe('RiddleFormComponent', () => {
  let component: RiddleFormComponent;
  let fixture: ComponentFixture<RiddleFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RiddleFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RiddleFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
